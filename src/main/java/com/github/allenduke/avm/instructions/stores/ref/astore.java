package com.github.allenduke.avm.instructions.stores.ref;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.heap.AObject;

public class astore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x3a;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject reference = frame.getOperandStack().popRef();
        frame.getSlots().setRef(index, reference);
    }
}
