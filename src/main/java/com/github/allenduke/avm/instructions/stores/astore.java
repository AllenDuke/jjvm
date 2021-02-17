package com.github.allenduke.avm.instructions.stores;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

import java.lang.ref.Reference;

public class astore extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x3a;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        Reference reference = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, reference);
    }
}
