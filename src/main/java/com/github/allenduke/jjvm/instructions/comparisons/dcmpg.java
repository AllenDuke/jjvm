package com.github.allenduke.jjvm.instructions.comparisons;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class dcmpg extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_dcmpg;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        double v2 = operandStack.popDouble();
        double v1 = operandStack.popDouble();
        if (v1 > v2)
            operandStack.pushInt(1);
        else if (v1 == v2)
            operandStack.pushInt(0);
        else if (v1 < v2)
            operandStack.pushInt(-1);
        else
            operandStack.pushInt(1);
    }
}
