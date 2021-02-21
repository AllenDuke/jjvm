package com.github.allenduke.avm.instructions.loads.l;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class lload_3 extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_lload_3;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        long val = frame.getSlots().getLong(3);
        frame.getOperandStack().pushLong(val);
    }
}
