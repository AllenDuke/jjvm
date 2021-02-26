package com.github.allenduke.jjvm.instructions.extended;

import com.github.allenduke.jjvm.instructions.base.BranchInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class ifnull extends BranchInstruction {
    @Override
    public int getOpCode() {
        return CODE_ifnull;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        Object reference = operandStack.popRef();
        if (reference == null) {
            branch(frame,offset);
        }
    }
}
