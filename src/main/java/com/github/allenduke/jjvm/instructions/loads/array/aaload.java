package com.github.allenduke.jjvm.instructions.loads.array;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class aaload extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_aaload;
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        AObject arrRef = stack.popRef();

        if (arrRef == null) throw new RuntimeException("java.lang.NullPointerException");
        AObject[] refs = arrRef.refs();
        if (index < 0 || index >= refs.length) throw new RuntimeException("ArrayIndexOutOfBoundsException");
        stack.pushRef(refs[index]);
    }
}
