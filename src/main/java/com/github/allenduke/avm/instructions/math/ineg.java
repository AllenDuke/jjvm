package com.github.allenduke.avm.instructions.math;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

public class ineg extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x74;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt(operandStack.popInt() * -1);
    }
}
