package com.github.allenduke.avm.instructions.conversions;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

public class l2d extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x8a;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushDouble(operandStack.popLong());
    }
}
