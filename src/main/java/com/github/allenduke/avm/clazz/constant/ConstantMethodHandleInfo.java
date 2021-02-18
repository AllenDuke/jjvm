package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantMethodHandleInfo extends ConstantInfo {

    private int reference_kind;

    private int reference_index;

    @Override
    public int getTag() {
        return 15;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantMethodHandleInfo constant = new ConstantMethodHandleInfo();
        constant.setTag(getTag());
        constant.setReference_kind(classReader.readU1());
        constant.setReference_index(classReader.readU2());
        return constant;
    }
}
