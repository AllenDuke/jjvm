package com.github.allenduke.jjvm.instructions.math.div;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class ddiv extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_ddiv;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        double v2 = operandStack.popDouble();
        double v1 = operandStack.popDouble();
        if (v2 == 0) {
            //todo 遍历虚拟机栈
            throw new ArithmeticException("java.lang.ArithmeticException: / by zero");
        }
        operandStack.pushDouble(v1 / v2);
    }
}
