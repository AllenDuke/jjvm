package com.github.allenduke.avm.instructions.loads.ref;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.heap.AObject;

public class aload_1 extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x2b;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject ref = frame.getSlots().getRef(1);
        frame.getOperandStack().pushRef(ref);
    }
}
