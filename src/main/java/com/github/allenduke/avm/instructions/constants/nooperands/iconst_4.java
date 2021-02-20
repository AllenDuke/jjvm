package com.github.allenduke.avm.instructions.constants.nooperands;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class iconst_4 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_iconst_4;
    }

    @Override
    public String getReName() {
        return "iconst_4";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushInt(4);
    }
}
