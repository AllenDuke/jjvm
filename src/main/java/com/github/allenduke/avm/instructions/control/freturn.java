package com.github.allenduke.avm.instructions.control;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class freturn extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0xae;
    }

    @Override
    public void execute(Frame frame) throws Exception {

    }
}
