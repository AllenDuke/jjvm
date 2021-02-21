package com.github.allenduke.avm.classfile.constant;

import com.github.allenduke.avm.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantIntegerInfo extends ConstantInfo {

    private int value;

    @Override
    public int getTag() {
        return TAG_INTEGER;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantIntegerInfo constant = new ConstantIntegerInfo();
        constant.setTag(getTag());
        constant.setValue(classReader.readU4Int());
        return constant;
    }
}
