package com.github.allenduke.jjvm.instructions.loads.f;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class fload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_fload;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        float val = frame.getSlots().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
}
