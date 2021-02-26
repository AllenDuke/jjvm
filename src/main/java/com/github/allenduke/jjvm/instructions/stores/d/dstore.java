package com.github.allenduke.jjvm.instructions.stores.d;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class dstore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_dstore;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        double val = frame.getOperandStack().popDouble();
        frame.getSlots().setDouble(index, val);
    }
}
