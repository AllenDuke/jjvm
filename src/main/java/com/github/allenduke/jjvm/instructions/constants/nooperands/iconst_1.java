package com.github.allenduke.jjvm.instructions.constants.nooperands;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class iconst_1 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_iconst_1;
    }

    @Override
    public String getReName() {
        return "iconst_1";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushInt(1);
    }
}
