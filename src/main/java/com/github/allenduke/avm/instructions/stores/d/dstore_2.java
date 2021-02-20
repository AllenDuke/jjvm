package com.github.allenduke.avm.instructions.stores.d;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class dstore_2 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_dstore_2;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(2, val);
    }
}
