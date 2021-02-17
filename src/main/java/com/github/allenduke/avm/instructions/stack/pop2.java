package com.github.allenduke.avm.instructions.stack;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

public class pop2 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x58;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.popSlot();
        operandStack.popSlot();
    }
}
