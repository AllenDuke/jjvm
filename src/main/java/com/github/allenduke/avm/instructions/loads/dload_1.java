package com.github.allenduke.avm.instructions.loads;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;

public class dload_1 extends Index8Instruction {
    @Override
    public int getOpCode() {
        return 0x27;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        double val = frame.getLocalVars().getDouble(1);
        frame.getOperandStack().pushDouble(val);
    }
}
