package com.github.allenduke.jjvm.instructions.loads.array;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class baload extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_baload;
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        AObject arrRef = stack.popRef();

        if (arrRef == null) throw new RuntimeException("java.lang.NullPointerException");
        byte[] bytes = arrRef.bytes();
        if (index < 0 || index >= bytes.length) throw new RuntimeException("ArrayIndexOutOfBoundsException");
        stack.pushInt(bytes[index]);
    }
}
