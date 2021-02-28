package com.github.allenduke.jjvm.instructions.loads.array;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class iaload extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_iaload;
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        AObject arrRef = stack.popRef();

        if (arrRef == null) throw new RuntimeException("java.lang.NullPointerException");
        int[] ints = arrRef.ints();
        if (index < 0 || index >= ints.length) throw new RuntimeException("ArrayIndexOutOfBoundsException");
        stack.pushInt(ints[index]);
    }
}
