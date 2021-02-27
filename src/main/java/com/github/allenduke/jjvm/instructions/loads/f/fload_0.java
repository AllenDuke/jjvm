package com.github.allenduke.jjvm.instructions.loads.f;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class fload_0 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fload_0;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        float val = frame.getSlots().getFloat(0);
        frame.getOperandStack().pushFloat(val);
    }
}
