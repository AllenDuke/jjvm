package com.github.allenduke.jjvm.instructions.loads.l;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class lload_0 extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_lload_0;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getSlots().getLong(0);
        frame.getOperandStack().pushLong(val);
    }
}
