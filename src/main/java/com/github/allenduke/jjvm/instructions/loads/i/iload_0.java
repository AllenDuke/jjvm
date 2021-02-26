package com.github.allenduke.jjvm.instructions.loads.i;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class iload_0 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_iload_0;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        int val = frame.getSlots().getInt(0);
        frame.getOperandStack().pushInt(val);
    }
}
