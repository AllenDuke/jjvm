package com.github.allenduke.avm.instructions.conversions;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

public class d2f extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x90;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushFloat((float) operandStack.popDouble());
    }
}
