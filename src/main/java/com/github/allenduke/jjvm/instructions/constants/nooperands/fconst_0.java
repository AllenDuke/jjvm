package com.github.allenduke.jjvm.instructions.constants.nooperands;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class fconst_0 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_fconst_0;
    }

    @Override
    public String getReName() {
        return null;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushFloat(0.0f);
    }
}
