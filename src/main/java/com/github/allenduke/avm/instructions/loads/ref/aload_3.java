package com.github.allenduke.avm.instructions.loads.ref;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.heap.AObject;

public class aload_3 extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x2d;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject ref = frame.getSlots().getRef(3);
        frame.getOperandStack().pushRef(ref);
    }
}
