package com.github.allenduke.avm.instructions.stack;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;
import com.github.allenduke.avm.rtda.Slot;

public class dup extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_dup;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot = operandStack.popSlot();
        //fixme 这里只是再次压入了一个slot引用，并没有复制slot指向的堆内存
        operandStack.pushSlot(slot);
        operandStack.pushSlot(slot);
    }
}
