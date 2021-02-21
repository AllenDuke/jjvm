package com.github.allenduke.avm.instructions.loads.i;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class iload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return CODE_iload;
    }

    @Override
    public String getReName() {
        return "iload";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        int val = frame.getSlots().getInt(index);
        frame.getOperandStack().pushInt(val);
    }
}
