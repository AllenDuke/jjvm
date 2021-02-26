package com.github.allenduke.jjvm.classfile.constant;

import com.github.allenduke.jjvm.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantMethodHandleInfo extends ConstantInfo {

    private int referenceKind;

    private int referenceIndex;

    @Override
    public int getTag() {
        return TAG_METHOD_HANDLE;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantMethodHandleInfo constant = new ConstantMethodHandleInfo();
        constant.setTag(getTag());
        constant.setReferenceKind(classReader.readU1());
        constant.setReferenceIndex(classReader.readU2());
        return constant;
    }
}
