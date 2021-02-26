package com.github.allenduke.jjvm.instructions.conversions;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class d2i extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_d2i;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt((int) operandStack.popDouble());
    }
}
