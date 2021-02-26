package com.github.allenduke.jjvm.instructions.comparisons;

import com.github.allenduke.jjvm.instructions.base.BranchInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class ifeq extends BranchInstruction {
    @Override
    public int getOpCode() {
        return CODE_ifeq;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        int i = operandStack.popInt();
        if (i == 0) {
            branch(frame,offset);
        }
    }
}
