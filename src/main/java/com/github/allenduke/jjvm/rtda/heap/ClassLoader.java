package com.github.allenduke.jjvm.rtda.heap;

import com.github.allenduke.jjvm.classfile.ClassFile;
import com.github.allenduke.jjvm.classfile.ClassReader;
import com.github.allenduke.jjvm.classpath.Classpath;
import com.github.allenduke.jjvm.rtda.Slots;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public class ClassLoader {

    private Classpath classpath;

    /* key为类的全限定名，可以认为这是方法区或者元空间的一部分 */
    private Map<String, Class> classMap;

    public static ClassLoader newClassLoader(Classpath classpath) {
        ClassLoader classLoader = new ClassLoader();
        classLoader.classpath = classpath;
        classLoader.classMap = new HashMap<>();
        classLoader.loadBasicClasses();
        classLoader.loadPrimitiveClasses();
        return classLoader;
    }

    private void loadPrimitiveClasses() {
        ClassNameHelper.PRIMITIVE_TYPE_MAP.forEach((primitiveType, descp) -> {
            loadPrimitiveClass(primitiveType);
        });
    }

    // 基本类型的包装类没有父类
    private void loadPrimitiveClass(String className) {
        Class clazz = new Class();
        clazz.setAccessFlags(AccessFlags.ACC_PUBLIC);
        clazz.setName(className);
        clazz.setClassLoader(this);
        clazz.startInit();
        clazz.setJClass(classMap.get("java/lang/Class").newObject());
        clazz.getJClass().setExtra(clazz);
        classMap.put(className, clazz);
    }

    private void loadBasicClasses() {
        Class jlClassClass = loadClass("java/lang/Class");
        classMap.forEach((name, clazz) -> {
            if (clazz.getJClass() == null) {
                clazz.setJClass(jlClassClass.newObject());
                clazz.getJClass().setExtra(clazz);
            }
        });
    }

    public Class loadClass(String name) {
        name = name.replace(File.separatorChar, '/');
        Class clazz = classMap.get(name);
        if (clazz != null) return clazz;

        if (name.charAt(0) == '[') clazz = loadArrayClass(name);
        else clazz = loadNonArrayClass(name);

        Class jlClassClass = classMap.get("java/lang/Class");
        if (jlClassClass != null) {
            clazz.setJClass(jlClassClass.newObject());
            clazz.getJClass().setExtra(clazz);
        }

        classMap.put(name, clazz);
        return clazz;
    }

    private Class loadArrayClass(String name) {
        Class aClass = new Class();
        aClass.startInit();
        aClass.setAccessFlags(AccessFlags.ACC_PUBLIC); // todo
        aClass.setName(name);
        aClass.setClassLoader(this);
        aClass.setSuperClass(loadClass("java/lang/Object"));
        aClass.setInterfaces(new Class[]{loadClass("java/lang/Cloneable"), loadClass("java/io/Serializable")});
        return aClass;
    }

    private Class loadNonArrayClass(String name) {
        byte[] data = readClass(name);
        Class clazz = defineClass(data);
        link(clazz);
        System.out.println("loaded class " + name);
        return clazz;
    }

    private byte[] readClass(String name) {
        name = name.replace('\\', File.separatorChar);
        name = name.replace('/', File.separatorChar);
        try {
            return classpath.readClass(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("java.lang.ClassNotFoundException");
        }
    }

    private Class defineClass(byte[] data) {
        Class clazz = parseClass(data);
        clazz.setClassLoader(this);
        resolveSuperClass(clazz);
        return clazz;
    }

    private static Class parseClass(byte[] data) {
        ClassReader classReader = new ClassReader(data);
        ClassFile classFile = classReader.parseClassFile();
        return Class.newClass(classFile);
    }

    /* 递归加载父类 */
    private static void resolveSuperClass(Class clazz) {
        if (!clazz.getName().equals("java/lang/Object")) {
            clazz.setSuperClass(clazz.getClassLoader().loadClass(clazz.getSuperClassName()));
        }
    }

    /* 加载接口 */
    private static void resolveInterfaces(Class clazz) {
        long instanceSlotCount = clazz.getInstanceSlotCount();
        if (instanceSlotCount > 0) {
            clazz.setInterfaces(new Class[(int) instanceSlotCount]);
            Class[] interfaces = clazz.getInterfaces();
            String[] interfaceNames = clazz.getInterfaceNames();
            for (int i = 0; i < interfaces.length; i++) {
                interfaces[i] = clazz.getClassLoader().loadClass(interfaceNames[i]);
            }
        }
    }

    /* 链接分为验证和准备 */
    private static void link(Class clazz) {
        verify(clazz);
        prepare(clazz);
    }

    /* todo java虚拟机规范要求在执行类的任何代码前要先验证 */
    private static void verify(Class clazz) {

    }

    /* 准备阶段主要是给类变量分配空间并给予初始值 */
    private static void prepare(Class clazz) {
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
    }

    /* 计算实例字段个数，同时编号，从父类继承而来的较小 */
    private static void calcInstanceFieldSlotIds(Class clazz) {
        long slotId = 0;
        if (clazz.getSuperClass() != null) {
            slotId = clazz.getSuperClass().getInstanceSlotCount();
        }
        for (Field field : clazz.getFields()) {
            if (field.isStatic()) continue;
            field.setSlotId(slotId);
            slotId++;
            if (field.isLongOrDouble()) slotId++;
        }
        clazz.setInstanceSlotCount(slotId);
    }

    private static void calcStaticFieldSlotIds(Class clazz) {
        long slotId = 0;
        for (Field field : clazz.getFields()) {
            if (!field.isStatic()) continue;
            field.setSlotId(slotId);
            slotId++;
            if (field.isLongOrDouble()) slotId++;
        }
        clazz.setStaticSlotCount(slotId);
    }

    /* 为类变量分配空间，赋初值 */
    private static void allocAndInitStaticVars(Class clazz) {
        clazz.setStaticVars(new Slots((int) clazz.getStaticSlotCount()));
        for (Field field : clazz.getFields()) {
            if (field.isStatic() && field.isFinal())
                initStaticFinalVar(clazz, field);
        }
    }

    private static void initStaticFinalVar(Class clazz, Field field) {
        Slots staticVars = clazz.getStaticVars();
        ConstantPool constantPool = clazz.getConstantPool();
        long constValueIndex = field.getConstValueIndex();
        long slotId = field.getSlotId();

        if (constValueIndex > 0) {
            switch (field.getDescriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    Integer i = (Integer) constantPool.getConstant((int) constValueIndex);
                    staticVars.setInt((int) slotId, i);
                    break;
                case "J":
                    Long l = (Long) constantPool.getConstant((int) constValueIndex);
                    staticVars.setLong((int) slotId, l);
                    break;
                case "F":
                    Float f = (Float) constantPool.getConstant((int) constValueIndex);
                    staticVars.setFloat((int) slotId, f);
                    break;
                case "D":
                    Double d = (Double) constantPool.getConstant((int) constValueIndex);
                    staticVars.setDouble((int) slotId, d);
                    break;
                case "Ljava/lang/String;":
                    String str = (String) constantPool.getConstant((int) constValueIndex);
                    AObject jstr = StringPool.JString(clazz.getClassLoader(), str);
                    staticVars.setRef((int) slotId, jstr);
            }
        }
    }
}
