package com.github.allenduke.jjvm.instructions.math.shr;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class lushr extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_lushr;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        long v2 = operandStack.popInt();
        long v1 = operandStack.popLong();
        long s = v2 & 0x3f;
        operandStack.pushLong(v1 >>> s);
    }
}
