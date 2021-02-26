package com.github.allenduke.jjvm.instructions.loads.array;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class xaload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0;
    }

    @Override
    public void execute(Frame frame) {

    }
}
