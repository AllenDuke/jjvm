package com.github.allenduke.jjvm.instructions.loads.array;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class laload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x2f;
    }

    @Override
    public void execute(Frame frame) {

    }
}
