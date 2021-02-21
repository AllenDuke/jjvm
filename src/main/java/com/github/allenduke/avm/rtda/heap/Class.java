package com.github.allenduke.avm.rtda.heap;

import com.github.allenduke.avm.classfile.ClassFile;
import com.github.allenduke.avm.rtda.Slot;
import com.github.allenduke.avm.rtda.Slots;
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

    public boolean isPublic() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_PUBLIC);
    }

    /* 取包名 */
    public String getPackageName() {
        int i = name.lastIndexOf("/");
        if (i == -1) return "";
        return name.substring(0, i);
    }

    /* 当前类是否接受other访问，取决于当前是否public，或者当前与other同包 */
    public boolean isAccessibleTo(Class other) {
        return isPublic() || getPackageName().equals(other.getPackageName());
    }

    public boolean isSubClassOf(Class clazz) {
        Class pre = this.superClass;
        while (pre != null) {
            if (pre == clazz) return true;
            pre = pre.superClass;
        }
        return false;
    }

    public boolean isInterface() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_ABSTRACT);
    }
}
