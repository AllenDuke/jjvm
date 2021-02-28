package com.github.allenduke.jjvm.instructions.loads.array;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class saload extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_saload;
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        AObject arrRef = stack.popRef();

        if (arrRef == null) throw new RuntimeException("java.lang.NullPointerException");
        short[] shorts = arrRef.shorts();
        if (index < 0 || index >= shorts.length) throw new RuntimeException("ArrayIndexOutOfBoundsException");
        stack.pushInt(shorts[index]);
    }
}
