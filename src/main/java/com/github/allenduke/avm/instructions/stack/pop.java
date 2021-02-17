package com.github.allenduke.avm.instructions.stack;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class pop extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x57;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().popSlot();
    }
}
