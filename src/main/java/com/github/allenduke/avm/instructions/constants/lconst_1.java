package com.github.allenduke.avm.instructions.constants;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class lconst_1 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return 0x0a;
    }

    @Override
    public String getReName() {
        return "lconst_1";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushLong(1);
    }
}
