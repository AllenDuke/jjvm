package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CONSTANT_Inter_info extends CONSTANT {

    private int value;

    @Override
    public int getTag() {
        return 3;
    }

    @Override
    public CONSTANT parse(ClassReader classReader) {
        CONSTANT_Inter_info constant = new CONSTANT_Inter_info();
        constant.setTag(getTag());
        constant.setValue(classReader.readU4Int());
        return constant;
    }
}
