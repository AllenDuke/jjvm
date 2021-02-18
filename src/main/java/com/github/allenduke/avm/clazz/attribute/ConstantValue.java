package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import com.github.allenduke.avm.clazz.constant.ConstantInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantValue extends AttributeInfo {

    private ConstantInfo constantInfo;

    @Override
    public String getName() {
        return "ConstantValue";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        if (!getName().equals(getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        if (2 != getInfo().length || 2 != getAttributeLength()) {
            throw new RuntimeException("parse source file exception");
        }
        int pool_index = read(2);
        ConstantInfo constant = classFile.getConstantPool()[pool_index];
        setConstantInfo(constant);
        return this;
    }
}
