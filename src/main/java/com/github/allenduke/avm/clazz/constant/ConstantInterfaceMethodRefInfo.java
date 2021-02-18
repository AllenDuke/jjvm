package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantInterfaceMethodRefInfo extends ConstantInfo {

    private int classIndex;

    private int nameAndTypeIndex;

    @Override
    public int getTag() {
        return TAG_INTERFACE_METHOD_REF;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantInterfaceMethodRefInfo constant = new ConstantInterfaceMethodRefInfo();
        constant.setTag(getTag());
        constant.setClassIndex(classReader.readU2());
        constant.setNameAndTypeIndex(classReader.readU2());
        return constant;
    }
}
