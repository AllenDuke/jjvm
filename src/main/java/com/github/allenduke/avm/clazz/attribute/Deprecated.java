package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
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
        if (!getName().equals(getAttribute_name())) {
            throw new RuntimeException("parse source file exception");
        }
        if (0 != getInfo().length || 0 != getAttribute_length()) {
            throw new RuntimeException("parse source file exception");
        }
        return this;
    }
}
