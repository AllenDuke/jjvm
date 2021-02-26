package com.github.allenduke.jjvm.instructions.comparisons;

import com.github.allenduke.jjvm.instructions.base.BranchInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class if_icmpeq extends BranchInstruction {
    @Override
    public int getOpCode() {
        return CODE_if_icmpeq;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if (v2 == v1) {
            branch(frame,offset);
        }
    }
}
