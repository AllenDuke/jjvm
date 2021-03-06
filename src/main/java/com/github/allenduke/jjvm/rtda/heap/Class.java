package com.github.allenduke.jjvm.rtda.heap;

import com.github.allenduke.jjvm.classfile.ClassFile;
import com.github.allenduke.jjvm.rtda.Slots;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public class Class {
    private int accessFlags;    /* uint16 */

    private String name;

    private String superClassName;

    private String[] interfaceNames;

    private ConstantPool constantPool;

    private Field[] fields;

    private Method[] methods;

    private ClassLoader classLoader;

    private Class superClass;

    private Class[] interfaces;

    private long instanceSlotCount;     /* uint */

    private long staticSlotCount;       /* uint */

    private Slots staticVars;

    private boolean initStarted;        /* 是否已经初始化 */

    private AObject jClass;     /* 自身的镜像 java/lang/Class */

    public static Class newClass(ClassFile classFile) {
        Class clazz = new Class();
        clazz.accessFlags = classFile.getAccessFlags();
        clazz.name = classFile.getClassName();
        clazz.superClassName = classFile.getSuperClassName();
        clazz.interfaceNames = classFile.getInterfaceNames();
        clazz.constantPool = ConstantPool.newConstantPool(clazz, classFile.getConstantPool());
        clazz.fields = Field.newFields(clazz, classFile.getFields());
        clazz.methods = Method.newMethods(clazz, classFile.getMethods());
        return clazz;
    }

    public void startInit() {
        initStarted = true;
    }

    public Method getMainMethod() {
        return getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    /* 取包名 */
    public String getPackageName() {
        int i = name.lastIndexOf("/");
        if (i == -1) return "";
        return name.substring(0, i);
    }

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    /* 当前类是否接受other访问，取决于当前是否public，或者当前与other同包 */
    public boolean isAccessibleTo(Class other) {
        return isPublic() || getPackageName().equals(other.getPackageName());
    }

    public boolean isInterface() {
        return 0 != (accessFlags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    /* 判断当前类是否是clazz的子类 */
    public boolean isSubClassOf(Class clazz) {
        Class pre = this.superClass;
        while (pre != null) {
            if (pre == clazz) return true;
            pre = pre.superClass;
        }
        return false;
    }

    /* 判断当前是否实现了iface */
    public boolean isImplements(Class iface) {
        Class cur = this;
        while (cur != null) {
            for (Class i : cur.getInterfaces()) {
                if (i == iface || i.isSubInterfaceOf(iface)) return true;
            }
            cur = cur.superClass;
        }
        return false;
    }

    /* 判断当前接口是否继承了iface */
    private boolean isSubInterfaceOf(Class iface) {
        for (Class superInterface : this.getInterfaces()) {
            if (superInterface == iface || superInterface.isSubInterfaceOf(iface)) return true;
        }
        return false;
    }

    public boolean IsAssignableFrom(Class other) {
        if (other == this) return true;

        if (!other.isArray()) {
            if (!other.isInterface()) {
                if (!this.isInterface()) return other.isSubClassOf(this);
                else return other.isImplements(this);
            } else {
                if (!this.isInterface()) return this.isJlObject();
                else return this.isSuperInterfaceOf(other);
            }
        } else {
            if (!this.isArray()) {
                if (!this.isInterface()) return this.isJlObject();
                else return this.isJlCloneable() || this.isJioSerializable();

            } else {
                Class otherComponentClass = other.getComponentClass();
                Class thisComponentClass = this.getComponentClass();
                return otherComponentClass == thisComponentClass || thisComponentClass.IsAssignableFrom(otherComponentClass);
            }
        }

    }

    private boolean isJioSerializable() {
        return false;
    }

    private boolean isJlCloneable() {
        return false;
    }

    private boolean isSuperInterfaceOf(Class other) {
        return false;
    }

    private boolean isJlObject() {
        return false;
    }

    private boolean isArray() {
        return this.name.charAt(0) == '[';
    }

    public boolean isSuperClassOf(Class other) {
        return other.isSubClassOf(this);
    }

    public boolean isSuper() {
        return 0 != (accessFlags & AccessFlags.ACC_SUPER);
    }

    public Method getClinitMethod() {
        return this.getStaticMethod("<clinit>", "()V");
    }

    private Method getStaticMethod(String name, String descriptor) {
        for (Method m : methods) {
            if (name.equals(m.getName()) && descriptor.equals(m.getDescriptor())) {
                return m;
            }
        }
        return null;
    }

    public AObject newArray(int length) {
        if (!this.isArray()) {
            throw new RuntimeException("Not array class: " + this.name);
        }

        AObject ref = AObject.newObject(this);
        switch (this.name) {
            case "[Z":
            case "[B":
                ref.setData(new byte[length]);
                return ref;
            case "[C":
                ref.setData(new char[length]);
                return ref;
            case "[S":
                ref.setData(new short[length]);
                return ref;
            case "[I":
                ref.setData(new int[length]);
                return ref;
            case "[J":
                ref.setData(new long[length]);
                return ref;
            case "[F":
                ref.setData(new float[length]);
                return ref;
            case "[D":
                ref.setData(new double[length]);
                return ref;
            default:
                ref.setData(new AObject[length]);
                return ref;

        }
    }

    public Class getComponentClass() {
        String componentClassName = ClassNameHelper.getComponentClassName(this.name);
        return this.classLoader.loadClass(componentClassName);
    }

    public Class arrayClass() {
        String arrayClassName = ClassNameHelper.getArrayClassName(this.name);
        return this.classLoader.loadClass(arrayClassName);
    }

    public AObject newObject() {
        return AObject.newObject(this);
    }

    public Field getField(String name, String descriptor, boolean isStatic) {
        Class cur = this;
        while (cur != null) {
            for (Field field : cur.fields) {
                if (field.isStatic() == isStatic && field.getName().equals(name) && field.getDescriptor().equals(descriptor))
                    return field;
            }
            cur = cur.superClass;
        }
        return null;
    }

    public String getJavaName() {
        return this.name.replace('/', '.');
    }

    public boolean isPrimitive() {
        return ClassNameHelper.PRIMITIVE_TYPE_MAP.containsKey(this.name);
    }
}
