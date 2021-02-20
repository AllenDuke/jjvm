package com.github.allenduke.avm.instructions.comparisons;

import com.github.allenduke.avm.instructions.base.BranchInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

import java.lang.ref.Reference;

public class if_acmpne extends BranchInstruction {
    @Override
    public int getOpCode() {
        return CODE_if_acmpne;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        Object v2 = operandStack.popRef();
        Object v1 = operandStack.popRef();
        if (!v1.equals(v2)) {
            branch(frame,offset);
        }
    }
}
