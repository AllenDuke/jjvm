package com.github.allenduke.avm.instructions.loads;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class lload_3 extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x21;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getLocalVars().getLong(2);
        frame.getOperandStack().pushLong(val);
    }
}
