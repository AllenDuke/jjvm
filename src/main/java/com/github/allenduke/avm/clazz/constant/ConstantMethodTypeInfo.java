package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantMethodTypeInfo extends ConstantInfo {

    private int descriptor_index;

    @Override
    public int getTag() {
        return 16;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantMethodTypeInfo constant = new ConstantMethodTypeInfo();
        constant.setTag(getTag());
        constant.setDescriptor_index(classReader.readU2());
        return constant;
    }
}
