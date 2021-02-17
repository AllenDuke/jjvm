package com.github.allenduke.avm.instructions.stores;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class lstore_3 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x42;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(3, val);
    }
}
