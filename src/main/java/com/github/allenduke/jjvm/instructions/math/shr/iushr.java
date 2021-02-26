package com.github.allenduke.jjvm.instructions.math.shr;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;


public class iushr extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_iushr;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        int s = v2 & 0x1f;
        operandStack.pushInt(v1 >>> s); /* 无符号右移，高位用符号位填充 */
    }
}
