package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantFloatInfo extends ConstantInfo {


    private int value;

    @Override
    public int getTag() {
        return 4;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantFloatInfo constant = new ConstantFloatInfo();
        constant.setTag(getTag());
        constant.setValue(classReader.readU4Int());
        return constant;
    }
}
