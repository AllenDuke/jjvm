package com.github.allenduke.jjvm.instructions.loads.ref;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class aload_3 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_aload_3;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject ref = frame.getSlots().getRef(3);
        frame.getOperandStack().pushRef(ref);
    }
}
