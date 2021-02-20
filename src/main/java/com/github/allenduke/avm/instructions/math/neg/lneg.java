package com.github.allenduke.avm.instructions.math.neg;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

public class lneg extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_lneg;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushLong(operandStack.popLong() * -1);
    }
}
