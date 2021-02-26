package com.github.allenduke.jjvm.classfile.constant;

import com.github.allenduke.jjvm.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantUtf8Info extends ConstantInfo {

    private int length;

    private byte[] bytes;

    @Override
    public int getTag() {
        return TAG_UTF8;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantUtf8Info constant = new ConstantUtf8Info();
        constant.setTag(getTag());
        constant.setLength(classReader.readU2());
        byte[] bs = new byte[constant.getLength()];
        for (int i = 0; i < constant.getLength(); i++) {
            bs[i] = classReader.readByte();
        }
        constant.setBytes(bs);
        return constant;
    }

    public String parseString() {
        return new String(bytes);
    }
}
