package com.github.allenduke.jjvm.instructions.conversions;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class i2d extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_i2d;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushDouble(operandStack.popInt());
    }
}
