package com.github.allenduke.jjvm.instructions.stores.ref;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class astore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_astore;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject reference = frame.getOperandStack().popRef();
        frame.getSlots().setRef(index, reference);
    }
}
