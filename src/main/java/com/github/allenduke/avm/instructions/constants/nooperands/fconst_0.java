package com.github.allenduke.avm.instructions.constants.nooperands;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

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
