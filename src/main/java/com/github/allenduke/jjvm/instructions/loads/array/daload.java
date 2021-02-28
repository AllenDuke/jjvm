package com.github.allenduke.jjvm.instructions.loads.array;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class daload extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_daload;
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        AObject arrRef = stack.popRef();

        if (arrRef == null) throw new RuntimeException("java.lang.NullPointerException");
        double[] doubles = arrRef.doubles();
        if (index < 0 || index >= doubles.length) throw new RuntimeException("ArrayIndexOutOfBoundsException");
        stack.pushDouble(doubles[index]);
    }
}
