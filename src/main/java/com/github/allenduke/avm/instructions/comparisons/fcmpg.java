package com.github.allenduke.avm.instructions.comparisons;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;

public class fcmpg extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fcmpg;
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
        else    /* 如果有NaN，fcmpg指令返回1 */
            operandStack.pushInt(1);
    }
}
