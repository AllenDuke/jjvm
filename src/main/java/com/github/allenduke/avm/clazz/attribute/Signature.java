package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import com.github.allenduke.avm.clazz.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Signature extends AttributeInfo {

    private String signature;

    @Override
    public String getName() {
        return "Signature";
    }

    @Override
    public Signature parseAttribute(ClassFile classFile) {
        if (!getName().equals(this.getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        int index = read(2);
        ConstantUtf8Info constant_utf8_info = (ConstantUtf8Info) classFile.getConstantPool()[index];
        setSignature(constant_utf8_info.parseString());
        return this;
    }
}
