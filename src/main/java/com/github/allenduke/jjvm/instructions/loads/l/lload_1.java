package com.github.allenduke.jjvm.instructions.loads.l;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class lload_1 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_lload_1;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getSlots().getLong(1);
        frame.getOperandStack().pushLong(val);
    }
}
