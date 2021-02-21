package com.github.allenduke.avm.classfile.attribute;

import com.github.allenduke.avm.classfile.ClassFile;
import com.github.allenduke.avm.classfile.constant.ConstantInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantValueAttribute extends AttributeInfo {

    private ConstantInfo constantInfo;

    @Override
    public String getName() {
        return "ConstantValue";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        if (!getName().equals(getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        if (2 != getInfo().length || 2 != getAttributeLength()) {
            throw new RuntimeException("parse source file exception");
        }
        int pool_index = read(2);
        setIndex(pool_index);   // fixme 原为setIndex(0)
        ConstantInfo constant = classFile.getConstantPool()[pool_index];
        setConstantInfo(constant);
        return this;
    }
}
