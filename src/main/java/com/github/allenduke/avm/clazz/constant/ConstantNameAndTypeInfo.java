package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantNameAndTypeInfo extends ConstantInfo {


    private int name_index;

    private int descriptor_index;

    @Override
    public int getTag() {
        return 12;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantNameAndTypeInfo constant = new ConstantNameAndTypeInfo();
        constant.setTag(getTag());
        constant.setName_index(classReader.readU2());
        constant.setDescriptor_index(classReader.readU2());
        return constant;
    }
}