package com.github.allenduke.avm.instructions.stores.d;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class dstore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_dstore;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }
}
