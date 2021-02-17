package com.github.allenduke.avm.instructions.comparisons;

import com.github.allenduke.avm.instructions.base.BranchInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

public class if_icmpgt extends BranchInstruction {
    @Override
    public int getOpCode() {
        return 0xa3;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if (v2 < v1) {
            branch(frame,offset);
        }
    }
}
