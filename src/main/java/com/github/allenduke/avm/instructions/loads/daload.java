package com.github.allenduke.avm.instructions.loads;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class daload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x31;
    }

    @Override
    public void execute(Frame frame) {

    }
}
