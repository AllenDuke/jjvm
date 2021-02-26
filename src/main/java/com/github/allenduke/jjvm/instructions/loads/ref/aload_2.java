package com.github.allenduke.jjvm.instructions.loads.ref;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class aload_2 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_aload_2;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject ref = frame.getSlots().getRef(2);
        frame.getOperandStack().pushRef(ref);
    }
}
