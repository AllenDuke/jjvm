package com.github.allenduke.avm.classfile.attribute;

import com.github.allenduke.avm.classfile.ClassFile;
import com.github.allenduke.avm.classfile.constant.ConstantUtf8Info;
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
        ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) classFile.getConstantPool()[index];
        setSignature(constantUtf8Info.parseString());
        return this;
    }
}
