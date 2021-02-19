package com.github.allenduke.avm.instructions.stores;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

import java.lang.ref.Reference;

public class astore_0 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x4b;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        Object ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(0, ref);
    }
}
