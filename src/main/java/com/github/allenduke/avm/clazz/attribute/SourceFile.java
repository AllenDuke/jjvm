package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import com.github.allenduke.avm.clazz.ClassReader;
import com.github.allenduke.avm.clazz.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceFile extends AttributeInfo {

    private String sourceFile;

    @Override
    public String getName() {
        return "SourceFile";
    }

    @Override
    public SourceFile parseAttribute(ClassFile classFile) {
        if (!getName().equals(this.getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        if (2 != this.getAttributeLength()) {
            throw new RuntimeException("parse source file exception");
        }
        if (2 != this.getInfo().length) {
            throw new RuntimeException("parse source file exception");
        }
        int index = ClassReader.bytesToInt(this.getInfo());
        ConstantUtf8Info constant_utf8_info = (ConstantUtf8Info) classFile.getConstantPool()[index];
        sourceFile = constant_utf8_info.parseString();
        return this;
    }
}
