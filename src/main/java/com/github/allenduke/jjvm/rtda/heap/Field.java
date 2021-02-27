package com.github.allenduke.jjvm.rtda.heap;

import com.github.allenduke.jjvm.classfile.attribute.ConstantValueAttribute;
import com.github.allenduke.jjvm.classfile.field.FieldInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public class Field {

    private int accessFlags;

    private String name;

    private String descriptor;

    private String signature;

    private byte[] annotationData; // RuntimeVisibleAnnotations_attribute

    private Class clazz;

    private long constValueIndex;   /* uint */

    private long slotId;            /* uint */

    public void copyFieldInfo(FieldInfo info) {
        this.accessFlags = info.getAccessFlag();
        this.name = info.getName();
        this.descriptor = info.getDescriptor();
        ConstantValueAttribute constantValueAttribute = info.getConstantValueAttribute();
        if (constantValueAttribute != null)
            this.constValueIndex = constantValueAttribute.getIndex();
    }

    public static Field[] newFields(Class clazz, FieldInfo[] fieldInfos) {
        Field[] fields = new Field[fieldInfos.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new Field();
            fields[i].clazz = clazz;
            fields[i].copyFieldInfo(fieldInfos[i]);
        }
        return fields;
    }

    public boolean isStatic() {
        return 0 != (accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isLongOrDouble() {
        return descriptor.equals("J") || descriptor.equals("D");
    }

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isProtected() {
        return 0 != (accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isAccessibleTo(Class other) {
        if (isPublic()) return true;
        if (isProtected()) {
            return other == clazz || other.isSubClassOf(clazz) || clazz.getPackageName().equals(other.getPackageName());
        }
        if (!isPrivate()) {
            return clazz.getPackageName().equals(other.getPackageName());
        }
        return clazz == other;
    }

}
