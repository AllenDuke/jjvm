package com.github.allenduke.avm.instructions.stores.d;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class dstore_0 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_dstore_0;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(0, val);
    }
}
