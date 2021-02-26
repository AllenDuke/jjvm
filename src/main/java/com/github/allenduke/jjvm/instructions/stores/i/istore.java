package com.github.allenduke.jjvm.instructions.stores.i;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class istore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_istore;
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        frame.getSlots().setInt(index, val);
    }
}
