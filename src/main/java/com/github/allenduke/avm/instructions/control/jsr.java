package com.github.allenduke.avm.instructions.control;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class jsr extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0xa8;
    }

    @Override
    public void execute(Frame frame) throws Exception {

    }
}
