package com.github.allenduke.avm.instructions.stores.l;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class lstore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_lstore;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }
}
