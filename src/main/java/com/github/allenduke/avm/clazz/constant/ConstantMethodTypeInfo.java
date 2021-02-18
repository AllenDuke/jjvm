package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantMethodTypeInfo extends ConstantInfo {

    private int descriptorIndex;

    @Override
    public int getTag() {
        return TAG_METHOD_TYPE;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantMethodTypeInfo constant = new ConstantMethodTypeInfo();
        constant.setTag(getTag());
        constant.setDescriptorIndex(classReader.readU2());
        return constant;
    }
}
