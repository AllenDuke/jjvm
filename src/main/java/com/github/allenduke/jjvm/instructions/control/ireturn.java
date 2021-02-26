package com.github.allenduke.jjvm.instructions.control;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class ireturn extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0xac;
    }

    @Override
    public void execute(Frame frame) throws Exception {

    }
}
