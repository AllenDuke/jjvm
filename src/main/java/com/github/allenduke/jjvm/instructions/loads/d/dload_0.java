package com.github.allenduke.jjvm.instructions.loads.d;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class dload_0 extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_dload_0;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        double val = frame.getSlots().getDouble(0);
        frame.getOperandStack().pushDouble(val);
    }
}
