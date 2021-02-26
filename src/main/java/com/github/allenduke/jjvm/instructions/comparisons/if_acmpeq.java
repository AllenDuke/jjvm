package com.github.allenduke.jjvm.instructions.comparisons;

import com.github.allenduke.jjvm.instructions.base.BranchInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class if_acmpeq extends BranchInstruction {
    @Override
    public int getOpCode() {
        return CODE_if_acmpeq;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        Object v2 = operandStack.popRef();
        Object v1 = operandStack.popRef();
        if (v1.equals(v2)) {
            branch(frame,offset);
        }
    }
}
