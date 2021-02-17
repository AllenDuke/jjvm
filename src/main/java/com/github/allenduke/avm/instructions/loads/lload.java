package com.github.allenduke.avm.instructions.loads;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class lload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x16;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }
}
