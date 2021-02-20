package com.github.allenduke.avm.instructions.stores.l;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class lstore_1 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_lstore_1;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(1, val);
    }
}
