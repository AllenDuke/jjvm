package com.github.allenduke.avm.classfile.attribute;

import com.github.allenduke.avm.classfile.ClassFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceFileDebugExtension extends AttributeInfo {

    private String debugExtension;

    @Override
    public String getName() {
        return "SourceFileDebugExtension";
    }

    @Override
    public SourceFileDebugExtension parseAttribute(ClassFile classFile) {
        if (!getName().equals(this.getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        debugExtension = new String(getInfo());
        return this;
    }
}