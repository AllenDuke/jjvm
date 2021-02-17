package com.github.allenduke.avm.instructions.constants;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class aconst_null extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return 0x01;
    }

    @Override
    public String getReName() {
        return "aconst_null";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushRef(null);
    }
}
