package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantDoubleInfo extends ConstantInfo {

    private int high_bytes;

    private int low_bytes;

    @Override
    public int getTag() {
        return 6;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantDoubleInfo constant = new ConstantDoubleInfo();
        constant.setTag(getTag());
        constant.setHigh_bytes(classReader.readU4Int());
        constant.setLow_bytes(classReader.readU4Int());
        return constant;
    }
}
