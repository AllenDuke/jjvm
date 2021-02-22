package com.github.allenduke.avm.rtda.heap;

import com.github.allenduke.avm.classfile.ClassFile;
import com.github.allenduke.avm.classfile.ClassReader;
import com.github.allenduke.avm.classpath.Classpath;
import com.github.allenduke.avm.rtda.Slots;

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

    /* key为类的全限定名 */
    private Map<String, Class> classMap;

    public static ClassLoader newClassLoader(Classpath classpath) {
        ClassLoader classLoader = new ClassLoader();
        classLoader.classpath = classpath;
        classLoader.classMap = new HashMap<>();
        return classLoader;
    }

    public Class loadClass(String name) {
        Class clazz = classMap.get(name);
        if (clazz == null) {
            clazz = loadNonArrayClass(name);
            classMap.put(name, clazz);
        }
        return clazz;
    }

    private Class loadNonArrayClass(String name) {
        byte[] data = readClass(name);
        Class clazz = defineClass(data);
        link(clazz);
        System.out.println("loaded class " + name);
        return clazz;
    }

    private byte[] readClass(String name) {
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

    /* java虚拟机规范要求在执行类的任何代码前要先验证 */
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
                    assert false; // todo
            }
        }
    }
}
