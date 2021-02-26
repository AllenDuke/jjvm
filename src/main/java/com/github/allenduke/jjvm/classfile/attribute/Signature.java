package com.github.allenduke.jjvm.classfile.attribute;

import com.github.allenduke.jjvm.classfile.ClassFile;
import com.github.allenduke.jjvm.classfile.constant.ConstantUtf8Info;
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
        if (constantUtf8Info != null)   //fixme 签名解析异常
            setSignature(constantUtf8Info.parseString());
        return this;
    }
}
