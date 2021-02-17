package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CONSTANT_NameAndType_info extends CONSTANT {


    private int name_index;

    private int descriptor_index;

    @Override
    public int getTag() {
        return 12;
    }

    @Override
    public CONSTANT parse(ClassReader classReader) {
        CONSTANT_NameAndType_info constant = new CONSTANT_NameAndType_info();
        constant.setTag(getTag());
        constant.setName_index(classReader.readU2());
        constant.setDescriptor_index(classReader.readU2());
        return constant;
    }
}
