package com.github.allenduke.jjvm.classfile.constant;

import com.github.allenduke.jjvm.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantNameAndTypeInfo extends ConstantInfo {


    private int nameIndex;

    private int descriptorIndex;

    @Override
    public int getTag() {
        return TAG_NAME_AND_TYPE;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantNameAndTypeInfo constant = new ConstantNameAndTypeInfo();
        constant.setTag(getTag());
        constant.setNameIndex(classReader.readU2());
        constant.setDescriptorIndex(classReader.readU2());
        return constant;
    }
}
