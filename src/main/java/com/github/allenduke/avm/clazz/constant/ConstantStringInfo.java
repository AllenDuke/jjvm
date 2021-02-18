package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantStringInfo extends ConstantInfo {

    private int string_index;

    @Override
    public int getTag() {
        return 8;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantStringInfo constant = new ConstantStringInfo();
        constant.setTag(getTag());
        constant.setString_index(classReader.readU2());
        return constant;
    }
}
