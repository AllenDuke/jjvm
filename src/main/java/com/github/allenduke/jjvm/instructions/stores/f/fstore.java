package com.github.allenduke.jjvm.instructions.stores.f;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class fstore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_fstore;
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getSlots().setFloat(index, val);
    }
}
