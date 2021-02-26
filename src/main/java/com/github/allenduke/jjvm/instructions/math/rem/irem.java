package com.github.allenduke.jjvm.instructions.math.rem;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class irem extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_irem;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if (v2 == 0) {
            throw new ArithmeticException("java.lang.ArithmeticException: / by zero");
        }
        operandStack.pushInt(v1 % v2);
    }
}
