package com.github.allenduke.jjvm.instructions.loads.d;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class dload_3 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_dload_3;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        double val = frame.getSlots().getDouble(3);
        frame.getOperandStack().pushDouble(val);
    }
}
