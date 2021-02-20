package com.github.allenduke.avm.instructions.constants.nooperands;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class dconst_0 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_dconst_0;
    }

    @Override
    public String getReName() {
        return "dconst_0";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushDouble(0.0d);
    }
}
