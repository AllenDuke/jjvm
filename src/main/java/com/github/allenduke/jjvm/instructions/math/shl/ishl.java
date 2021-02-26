package com.github.allenduke.jjvm.instructions.math.shl;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;


public class ishl extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_ishl;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        int s = v2 & 0x1f;              /* int变量只有32bit，s应<=32，只取v2的低5位即可 */
        operandStack.pushInt(v1 << s);
    }
}
