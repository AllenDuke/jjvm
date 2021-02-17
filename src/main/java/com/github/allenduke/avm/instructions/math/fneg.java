package com.github.allenduke.avm.instructions.math;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

public class fneg extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x76;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushFloat(operandStack.popFloat() * -1);
    }
}
