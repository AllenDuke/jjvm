package com.github.allenduke.jjvm.instructions.math.and;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class iand extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_iand;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        operandStack.pushInt(v1 & v2);
    }
}
