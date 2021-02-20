package com.github.allenduke.avm.instructions.loads.array;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class baload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x33;
    }

    @Override
    public void execute(Frame frame) {

    }
}
