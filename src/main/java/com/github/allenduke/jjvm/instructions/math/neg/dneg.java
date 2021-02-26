package com.github.allenduke.jjvm.instructions.math.neg;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class dneg extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_dneg;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushDouble(operandStack.popDouble() * -1);
    }
}
