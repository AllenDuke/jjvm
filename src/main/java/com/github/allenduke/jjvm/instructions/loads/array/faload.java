package com.github.allenduke.jjvm.instructions.loads.array;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class faload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x31;
    }

    @Override
    public void execute(Frame frame) {

    }
}
