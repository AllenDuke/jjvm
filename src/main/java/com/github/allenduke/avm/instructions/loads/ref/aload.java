package com.github.allenduke.avm.instructions.loads.ref;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.heap.AObject;

public class aload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_aload;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        AObject ref = frame.getSlots().getRef(index);
        frame.getOperandStack().pushRef(ref);
    }
}
