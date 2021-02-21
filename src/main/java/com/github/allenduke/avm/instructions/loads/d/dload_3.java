package com.github.allenduke.avm.instructions.loads.d;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class dload_3 extends Index8Instruction {
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
