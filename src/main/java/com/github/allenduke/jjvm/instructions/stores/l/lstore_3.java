package com.github.allenduke.jjvm.instructions.stores.l;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class lstore_3 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_lstore_3;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getOperandStack().popLong();
        frame.getSlots().setLong(3, val);
    }
}
