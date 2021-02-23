package com.github.allenduke.avm.rtda.heap;

import com.github.allenduke.avm.classfile.constant.*;
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
public class InterfaceMethodRef extends SymRef{

    private String name;

    private String descriptor;

    private Method method;

    public static InterfaceMethodRef newInterfaceMethodRef(ConstantPool constantPool, ConstantInfo[] constantInfos,
                                         ConstantInterfaceMethodRefInfo methodRefInfo) {
        InterfaceMethodRef methodRef = new InterfaceMethodRef();
        methodRef.setConstantPool(constantPool);
        ConstantClassInfo classInfo= (ConstantClassInfo) constantInfos[methodRefInfo.getClassIndex()];
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantInfos[classInfo.getNameIndex()];
        methodRef.setClassName(utf8Info.parseString());

        ConstantNameAndTypeInfo nameAndTypeInfo =
                (ConstantNameAndTypeInfo) constantInfos[methodRefInfo.getNameAndTypeIndex()];

        utf8Info = (ConstantUtf8Info) constantInfos[nameAndTypeInfo.getNameIndex()];
        methodRef.setName(utf8Info.parseString());

        utf8Info = (ConstantUtf8Info) constantInfos[nameAndTypeInfo.getDescriptorIndex()];
        methodRef.setDescriptor(utf8Info.parseString());
        return methodRef;
    }
}
