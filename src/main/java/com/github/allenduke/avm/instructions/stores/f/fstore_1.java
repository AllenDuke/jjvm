package com.github.allenduke.avm.instructions.stores.f;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class fstore_1 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fstore_1;
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getSlots().setFloat(1, val);
    }
}
