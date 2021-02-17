package com.github.allenduke.avm.instructions.stores;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class fstore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x38;
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }
}
