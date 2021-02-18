package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantFieldRef extends ConstantInfo {

    private int class_index;

    private int name_and_type_index;

    @Override
    public int getTag() {
        return 9;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantFieldRef constant = new ConstantFieldRef();
        constant.setTag(getTag());
        constant.setClass_index(classReader.readU2());
        constant.setName_and_type_index(classReader.readU2());
        return constant;
    }
}
