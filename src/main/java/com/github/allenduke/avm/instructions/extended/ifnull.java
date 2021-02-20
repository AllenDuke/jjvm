package com.github.allenduke.avm.instructions.extended;

import com.github.allenduke.avm.instructions.base.BranchInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

import java.lang.ref.Reference;

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
