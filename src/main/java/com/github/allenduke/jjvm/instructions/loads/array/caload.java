package com.github.allenduke.jjvm.instructions.loads.array;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class caload extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_caload;
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        AObject arrRef = stack.popRef();

        if (arrRef == null) throw new RuntimeException("java.lang.NullPointerException");
        char[] chars = arrRef.chars();
        if (index < 0 || index >= chars.length) throw new RuntimeException("ArrayIndexOutOfBoundsException");
        stack.pushInt(chars[index]);
    }
}
