package com.github.allenduke.jjvm.classfile.constant;

import com.github.allenduke.jjvm.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantClassInfo extends ConstantInfo {

    private int nameIndex;

    @Override
    public int getTag() {
        return TAG_CLASS;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantClassInfo constant_class = new ConstantClassInfo();
        constant_class.setTag(getTag());
        constant_class.setNameIndex(classReader.readU2());
        return constant_class;
    }
}
