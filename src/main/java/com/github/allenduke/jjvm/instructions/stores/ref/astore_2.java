package com.github.allenduke.jjvm.instructions.stores.ref;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class astore_2 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_astore_2;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject val = frame.getOperandStack().popRef();
        frame.getSlots().setRef(2, val);
    }
}
