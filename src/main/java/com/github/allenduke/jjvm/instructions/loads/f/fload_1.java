package com.github.allenduke.jjvm.instructions.loads.f;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class fload_1 extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_fload_1;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        float val = frame.getSlots().getFloat(1);
        frame.getOperandStack().pushFloat(val);
    }
}
