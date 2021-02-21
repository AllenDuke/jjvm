package com.github.allenduke.avm.classfile.constant;

import com.github.allenduke.avm.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantStringInfo extends ConstantInfo {

    private int stringIndex;

    @Override
    public int getTag() {
        return TAG_STRING;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantStringInfo constant = new ConstantStringInfo();
        constant.setTag(getTag());
        constant.setStringIndex(classReader.readU2());
        return constant;
    }
}
