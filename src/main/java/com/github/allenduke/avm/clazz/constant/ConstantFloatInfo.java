package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ConstantFloatInfo extends ConstantInfo {

    private float value;

    @Override
    public int getTag() {
        return TAG_FLOAT;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantFloatInfo constant = new ConstantFloatInfo();
        constant.setTag(getTag());

        byte[] b = classReader.readBytes(4);
        ByteBuffer buf = ByteBuffer.allocateDirect(4);
//        buf = buf.order(ByteOrder.LITTLE_ENDIAN);   /* 默认大端，小端用这行 */
        buf.put(b);
        buf.rewind();   /* 重置pos */
        float f = buf.getFloat();
        constant.setValue(f);
        return constant;
    }
}
