package com.github.allenduke.jjvm.instructions.math.div;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class fdiv extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fdiv;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        float v2 = operandStack.popFloat();
        float v1 = operandStack.popFloat();
        if (v2 == 0) {
            throw new ArithmeticException("java.lang.ArithmeticException: / by zero");
        }
        operandStack.pushFloat(v1 / v2);
    }
}
