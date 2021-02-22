package com.github.allenduke.avm.rtda.heap;

import com.github.allenduke.avm.classfile.constant.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Setter
@Getter
/**
 * @author allen
 * @description 字段符号引用，引用其他类的某个字段
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public class FieldRef extends SymRef {

    private String name;

    private String descriptor;

    private Field field;

    public static FieldRef newFieldRef(ConstantPool constantPool, ConstantInfo[] constantInfos,
                                       ConstantFieldRefInfo fieldRefInfo) {
        FieldRef fieldRef = new FieldRef();
        fieldRef.setConstantPool(constantPool);
        ConstantClassInfo classInfo=(ConstantClassInfo) constantInfos[fieldRefInfo.getClassIndex()];
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantInfos[classInfo.getNameIndex()];
        fieldRef.setClassName(utf8Info.parseString());

        ConstantNameAndTypeInfo nameAndTypeInfo =
                (ConstantNameAndTypeInfo) constantInfos[fieldRefInfo.getNameAndTypeIndex()];

        utf8Info = (ConstantUtf8Info) constantInfos[nameAndTypeInfo.getNameIndex()];
        fieldRef.setName(utf8Info.parseString());

        utf8Info = (ConstantUtf8Info) constantInfos[nameAndTypeInfo.getDescriptorIndex()];
        fieldRef.setDescriptor(utf8Info.parseString());
        return fieldRef;
    }

    public Field resolvedField() {
        if (field == null) {
            resolvedFieldRef();
        }
        return field;
    }

    private void resolvedFieldRef() {
        Class cpClazz = getConstantPool().getClazz();
        Class clazz = resolvedClass();
        field = lookupField(clazz, name, descriptor);

        if(field==null){
            throw new RuntimeException("java.lang.NoSuchFieldError");
        }

        if(!field.isAccessibleTo(cpClazz)){
            throw new RuntimeException("java.lang.IllegalAccessError");
        }
    }

    private Field lookupField(Class clazz, String name, String descriptor) {
        /* 在引用的类中找到该字段 */
        for (Field field : clazz.getFields()) {
            if (field.getName().equals(name) && field.getDescriptor().equals(descriptor))
                return field;
        }

        /* 在继承层次中递归寻找该字段 */

        /* 在引用的类的直接接口中寻找该字段 */
        for (Class anInterface : clazz.getInterfaces()) {
            Field field = lookupField(anInterface, name, descriptor);
            if (field != null) return field;
        }
        /* 在引用的类的父类中寻找该字段 */
        Class superClass = clazz.getSuperClass();
        if (superClass != null) {
            return lookupField(superClass, name, descriptor);
        }
        return null;
    }

}
