package com.github.allenduke.jjvm.instructions.comparisons;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;

public class fcmpl extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fcmpl;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        float v2 = operandStack.popFloat();
        float v1 = operandStack.popFloat();
        if (v1 > v2)
            operandStack.pushInt(1);
        else if (v1 == v2)
            operandStack.pushInt(0);
        else if (v1 < v2)
            operandStack.pushInt(-1);
        else    /* 如果有NaN，fcmpl指令返回-1 */
            operandStack.pushInt(-1);
    }
}
