package com.github.allenduke.jjvm.instructions.loads.f;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class fload_3 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fload_3;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        float val = frame.getSlots().getFloat(3);
        frame.getOperandStack().pushFloat(val);
    }
}
