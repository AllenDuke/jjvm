package com.github.allenduke.avm.instructions.comparisons;

import com.github.allenduke.avm.instructions.base.BranchInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

public class if_icmpne extends BranchInstruction {
    @Override
    public int getOpCode() {
        return CODE_if_icmpne;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if (v1 != v2) {
            branch(frame,offset);
        }
    }
}
