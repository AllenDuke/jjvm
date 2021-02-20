package com.github.allenduke.avm.instructions.loads.ref;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

import java.lang.ref.Reference;

public class aload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x19;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        Object ref = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(ref);
    }
}
