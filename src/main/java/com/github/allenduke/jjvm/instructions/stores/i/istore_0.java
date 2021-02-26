package com.github.allenduke.jjvm.instructions.stores.i;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class istore_0 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_istore_0;
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        frame.getSlots().setInt(0, val);
    }
}
