package com.github.allenduke.avm.instructions.loads;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class iload extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x15;
    }

    @Override
    public String getReName() {
        return "iload";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }
}
