package com.github.allenduke.avm.instructions.constants;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class ldc extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return 0x12;
    }

    @Override
    public String getReName() {
        return "ldc";
    }

    @Override
    public void execute(Frame frame) {
    }
}
