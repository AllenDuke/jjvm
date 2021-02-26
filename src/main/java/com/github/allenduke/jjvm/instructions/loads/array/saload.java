package com.github.allenduke.jjvm.instructions.loads.array;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class saload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x35;
    }

    @Override
    public void execute(Frame frame) {

    }
}
