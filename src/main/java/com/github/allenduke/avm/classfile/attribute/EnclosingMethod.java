package com.github.allenduke.avm.classfile.attribute;

import com.github.allenduke.avm.classfile.ClassFile;
import com.github.allenduke.avm.classfile.constant.ConstantClassInfo;
import com.github.allenduke.avm.classfile.constant.ConstantNameAndTypeInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnclosingMethod extends AttributeInfo {

    private ConstantClassInfo clazz;

    private ConstantNameAndTypeInfo method;

    @Override
    public String getName() {
        return "EnclosingMethod";
    }

    @Override
    public EnclosingMethod parseAttribute(ClassFile classFile) {
        if (!getName().equals(this.getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        if (4 != this.getAttributeLength()) {
            throw new RuntimeException("parse source file exception");
        }
        int clazz_index = read(2);

        ConstantClassInfo constant_class_info = (ConstantClassInfo) classFile.getConstantPool()[clazz_index];
        setClazz(constant_class_info);
        int method_index = read(2);
        if (method_index == 0) {
            setMethod(null);
        } else {
            ConstantNameAndTypeInfo constant_nameAndType_info = (ConstantNameAndTypeInfo) classFile.getConstantPool()[method_index];
            setMethod(constant_nameAndType_info);
        }
        return this;
    }
}
