package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceFileDebugExtension extends Attribute_info {

    private String debug_extension;

    @Override
    public String getName() {
        return "SourceFileDebugExtension";
    }

    @Override
    public SourceFileDebugExtension parseAttribute(ClassFile classFile) {
        if (!getName().equals(this.getAttribute_name())) {
            throw new RuntimeException("parse source file exception");
        }
        setDebug_extension(new String(getInfo()));
        return this;
    }
}