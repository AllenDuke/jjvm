package com.github.allenduke.jjvm.instructions.stack;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class pop2 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_pop2;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.popSlot();
        operandStack.popSlot();
    }
}
