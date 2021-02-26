package com.github.allenduke.jjvm.instructions.stores.f;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class fstore_1 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fstore_1;
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getSlots().setFloat(1, val);
    }
}
