package com.github.allenduke.avm.instructions.stores;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class lstore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x37;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }
}
