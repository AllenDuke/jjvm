package com.github.allenduke.jjvm.instructions.comparisons;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class lcmp extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_lcmp;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        long v2 = operandStack.popLong();
        long v1 = operandStack.popLong();
        if (v1 > v2)
            operandStack.pushInt(1);
        else if (v1 == v2)
            operandStack.pushInt(0);
        else
            operandStack.pushInt(-1);
    }
}
