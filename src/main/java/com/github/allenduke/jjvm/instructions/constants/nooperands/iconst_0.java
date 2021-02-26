package com.github.allenduke.jjvm.instructions.constants.nooperands;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class iconst_0 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_iconst_0;
    }

    @Override
    public String getReName() {
        return "iconst_0";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushInt(0);
    }
}
