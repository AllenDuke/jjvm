package com.github.allenduke.jjvm.instructions.math.neg;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class ineg extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_ineg;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt(operandStack.popInt() * -1);
    }
}
