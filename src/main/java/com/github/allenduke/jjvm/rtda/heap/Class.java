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
    private String accessFlags;    /* uint16 */

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

    public Method getMainMethod() {
        for (Method m : methods) {
            if ("main".equals(m.getName()) && "([Ljava/lang/String;)V".equals(m.getDescriptor())) {
                return m;
            }
        }
        return null;
    }

    /* 取包名 */
    public String getPackageName() {
        int i = name.lastIndexOf("/");
        if (i == -1) return "";
        return name.substring(0, i);
    }

    public boolean isPublic() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_PUBLIC);
    }

    /* 当前类是否接受other访问，取决于当前是否public，或者当前与other同包 */
    public boolean isAccessibleTo(Class other) {
        return isPublic() || getPackageName().equals(other.getPackageName());
    }

    public boolean isInterface() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_ABSTRACT);
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
        if (!this.isInterface()) return other.isSubClassOf(this);
        else return other.isImplements(this);
//        if (!other.isArray()) {
//            if (!other.isInterface()) {
//                if(!this.isInterface()) return other.isSubClassOf(this);
//                else return other.isImplements(this);
//            } else {
//                if(!this.isInterface()) return this.isJlObject();
//                else return this.isSuperInterfaceOf(other);
//            }
//        } else {
//            if (!this.isArray()) {
//                if (!this.isInterface()) return this.isJlObject();
//                else return this.isJlCloneable() || this.isJioSerializable();
//
//            } else{
//                sc:=s.ComponentClass();
//                tc:=t.ComponentClass()
//                return sc == tc || tc.IsAssignableFrom(sc)
//            }
//        }
//        return false;
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

        return false;
    }


    public boolean isSuperClassOf(Class other) {
        return other.isSubClassOf(this);
    }

    public boolean isSuper() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_SUPER);
    }
}
