package com.github.allenduke.jjvm.classfile.constant;

import com.github.allenduke.jjvm.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantMethodRefInfo extends ConstantInfo {


    private int classIndex;

    private int nameAndTypeIndex;

    @Override
    public int getTag() {
        return TAG_METHOD_REF;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantMethodRefInfo constant = new ConstantMethodRefInfo();
        constant.setTag(getTag());
        constant.setClassIndex(classReader.readU2());
        constant.setNameAndTypeIndex(classReader.readU2());
        return constant;
    }
}
