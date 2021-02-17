package com.github.allenduke.avm.instructions.stores;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class istore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x36;
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }
}
