package com.github.allenduke.avm.instructions.control;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class lreturn extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0xad;
    }

    @Override
    public void execute(Frame frame) throws Exception {

    }
}
