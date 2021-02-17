package com.github.allenduke.avm.instructions.extended;

import com.github.allenduke.avm.instructions.base.BranchInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

import java.lang.ref.Reference;

public class ifnotnull extends BranchInstruction {
    @Override
    public int getOpCode() {
        return 0xc7;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        Reference reference = operandStack.popRef();
        if (reference.get() != null) {
            branch(frame,offset);
        }
    }
}
