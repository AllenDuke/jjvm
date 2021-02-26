package com.github.allenduke.jjvm.classfile.constant;

import com.github.allenduke.jjvm.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantLongInfo extends ConstantInfo {

    private long value;

    @Override
    public int getTag() {
        return TAG_LONG;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantLongInfo constant = new ConstantLongInfo();
        constant.setTag(getTag());
        long lh = classReader.readU4();
        long ll = classReader.readU4();
        constant.setValue((lh << 32) | ll);
        return constant;
    }

}
