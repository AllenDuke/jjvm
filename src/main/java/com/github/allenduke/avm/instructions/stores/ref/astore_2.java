package com.github.allenduke.avm.instructions.stores.ref;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.heap.AObject;

public class astore_2 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x4d;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject val = frame.getOperandStack().popRef();
        frame.getSlots().setRef(2, val);
    }
}
