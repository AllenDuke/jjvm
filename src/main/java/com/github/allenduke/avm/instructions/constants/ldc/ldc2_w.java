package com.github.allenduke.avm.instructions.constants.ldc;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class ldc2_w extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return 0x14;
    }

    @Override
    public String getReName() {
        return "ldc2_w";
    }

    @Override
    public void execute(Frame frame) {
    }
}
