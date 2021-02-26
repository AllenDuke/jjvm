package com.github.allenduke.jjvm.instructions.control;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class dreturn extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0xaf;
    }

    @Override
    public void execute(Frame frame) throws Exception {

    }
}
