package com.github.allenduke.avm.instructions.loads.f;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

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
