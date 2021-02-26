package com.github.allenduke.jjvm.instructions.control;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class lreturn extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0xad;
    }

    @Override
    public void execute(Frame frame) throws Exception {

    }
}
