package com.github.allenduke.avm.instructions.loads.f;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class fload_3 extends Index8Instruction {
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