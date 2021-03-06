package com.github.allenduke.jjvm.classfile.attribute;

import com.github.allenduke.jjvm.classfile.ClassFile;
import com.github.allenduke.jjvm.classfile.constant.ConstantClassInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Exceptions extends AttributeInfo {

    private int number;

    private ConstantClassInfo[] exceptions;

    @Override
    public String getName() {
        return "Exceptions";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        if (!getName().equals(getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        setIndex(0);
        setNumber(read(2));
        ConstantClassInfo[] constant_class_infos = new ConstantClassInfo[getNumber()];
        setExceptions(constant_class_infos);
        for (int i = 0; i < constant_class_infos.length; i++) {
            ConstantClassInfo constant_class_info = (ConstantClassInfo) classFile.getConstantPool()[read(2)];
            exceptions[i] = constant_class_info;
        }
        return this;
    }
}
