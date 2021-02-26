package com.github.allenduke.jjvm.instructions.stores.ref;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class astore_1 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x4c;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject ref = frame.getOperandStack().popRef();
        frame.getSlots().setRef(1, ref);
    }
}
