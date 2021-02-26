package com.github.allenduke.jjvm.instructions.stores.l;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class lstore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_lstore;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getOperandStack().popLong();
        frame.getSlots().setLong(index, val);
    }
}
