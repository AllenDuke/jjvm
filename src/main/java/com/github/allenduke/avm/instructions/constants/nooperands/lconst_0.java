package com.github.allenduke.avm.instructions.constants.nooperands;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class lconst_0 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_lconst_0;
    }

    @Override
    public String getReName() {
        return "lconst_0";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushLong(0L);
    }
}
