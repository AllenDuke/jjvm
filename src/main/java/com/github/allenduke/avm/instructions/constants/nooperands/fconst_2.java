package com.github.allenduke.avm.instructions.constants.nooperands;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class fconst_2 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_fconst_2;
    }

    @Override
    public String getReName() {
        return "fconst_2";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushFloat(2.0f);
    }
}
