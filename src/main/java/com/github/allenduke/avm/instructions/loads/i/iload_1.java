package com.github.allenduke.avm.instructions.loads.i;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class iload_1 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_iload_1;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        int val = frame.getLocalVars().getInt(1);
        frame.getOperandStack().pushInt(val);
    }
}
