package com.github.allenduke.jjvm.instructions.math.sub;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class fsub extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fsub;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        float v2 = operandStack.popFloat();
        float v1 = operandStack.popFloat();
        operandStack.pushFloat(v1 - v2);
    }
}
