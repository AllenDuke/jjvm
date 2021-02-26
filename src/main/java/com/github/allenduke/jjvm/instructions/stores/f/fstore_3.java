package com.github.allenduke.jjvm.instructions.stores.f;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class fstore_3 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fstore_3;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        float val = frame.getOperandStack().popFloat();
        frame.getSlots().setFloat(3, val);
    }
}
