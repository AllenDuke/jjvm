package com.github.allenduke.jjvm.classfile.attribute;

import com.github.allenduke.jjvm.classfile.ClassFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Deprecated extends AttributeInfo {

    @Override
    public String getName() {
        return "Deprecated";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        if (!getName().equals(getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        if (0 != getInfo().length || 0 != getAttributeLength()) {
            throw new RuntimeException("parse source file exception");
        }
        return this;
    }
}
