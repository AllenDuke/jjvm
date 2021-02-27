package com.github.allenduke.jjvm.instructions.loads.d;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class dload_1 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_dload_1;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        double val = frame.getSlots().getDouble(1);
        frame.getOperandStack().pushDouble(val);
    }
}
