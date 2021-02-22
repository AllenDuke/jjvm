package com.github.allenduke.avm.instructions.stores.ref;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.heap.AObject;

public class astore_3 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x4e;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject ref = frame.getOperandStack().popRef();
        frame.getSlots().setRef(3, ref);
    }
}
