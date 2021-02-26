package com.github.allenduke.jjvm.instructions.stack;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.Slot;

public class dup2_x1 extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_dup2_x1;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        Slot slot3 = operandStack.popSlot();

        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot3);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }
}
