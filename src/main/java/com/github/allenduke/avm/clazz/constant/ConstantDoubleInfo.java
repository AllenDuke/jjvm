package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ConstantDoubleInfo extends ConstantInfo {

    private double value;

    @Override
    public int getTag() {
        return TAG_DOUBLE;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantDoubleInfo constant = new ConstantDoubleInfo();
        constant.setTag(getTag());
        byte[] b = classReader.readBytes(8);
        ByteBuffer buf = ByteBuffer.allocateDirect(8);
//        buf = buf.order(ByteOrder.LITTLE_ENDIAN);   /* 默认大端，小端用这行 */
        buf.put(b);
        buf.rewind();   /* 重置pos */
        double d = buf.getDouble();
        constant.setValue(d);
        return constant;
    }
}
