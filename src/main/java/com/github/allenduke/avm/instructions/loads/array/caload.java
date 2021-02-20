package com.github.allenduke.avm.instructions.loads.array;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class caload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x34;
    }

    @Override
    public void execute(Frame frame) {

    }
}
