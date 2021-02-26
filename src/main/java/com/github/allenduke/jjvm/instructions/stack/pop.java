package com.github.allenduke.jjvm.instructions.stack;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class pop extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_pop;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().popSlot();
    }
}
