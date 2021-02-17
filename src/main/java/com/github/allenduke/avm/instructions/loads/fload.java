package com.github.allenduke.avm.instructions.loads;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class fload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x17;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
}
