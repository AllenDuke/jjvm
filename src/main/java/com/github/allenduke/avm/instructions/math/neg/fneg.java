package com.github.allenduke.avm.instructions.math.neg;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

public class fneg extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fneg;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushFloat(operandStack.popFloat() * -1);
    }
}
