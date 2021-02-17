package com.github.allenduke.avm.instructions.math;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

/**

 */
public class lushr extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return 0x7d;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        long v2 = operandStack.popInt();
        long v1 = operandStack.popLong();
        long s = v2 & 0x3f;
        operandStack.pushLong(v1 >>> s);
    }
}
