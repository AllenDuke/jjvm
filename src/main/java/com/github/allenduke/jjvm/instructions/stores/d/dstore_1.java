package com.github.allenduke.jjvm.instructions.stores.d;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class dstore_1 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_dstore_1;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        double val = frame.getOperandStack().popDouble();
        frame.getSlots().setDouble(1, val);
    }
}
