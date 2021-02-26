package com.github.allenduke.jjvm.instructions.constants.nooperands;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class dconst_1 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_dconst_1;
    }

    @Override
    public String getReName() {
        return "dconst_1";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushDouble(1.0d);
    }
}
