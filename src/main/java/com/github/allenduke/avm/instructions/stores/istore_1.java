package com.github.allenduke.avm.instructions.stores;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class istore_1 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x3c;
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(1, val);
    }
}
