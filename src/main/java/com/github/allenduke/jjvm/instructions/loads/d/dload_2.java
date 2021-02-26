package com.github.allenduke.jjvm.instructions.loads.d;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class dload_2 extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_dload_2;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        double val = frame.getSlots().getDouble(2);
        frame.getOperandStack().pushDouble(val);
    }
}
