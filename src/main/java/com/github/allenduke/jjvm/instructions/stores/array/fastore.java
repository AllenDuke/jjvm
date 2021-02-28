package com.github.allenduke.jjvm.instructions.stores.array;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.AObject;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/28
 */
public class fastore extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_fastore;
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float val = stack.popFloat();
        int index = stack.popInt();
        AObject arrRef = stack.popRef();

        if (arrRef == null) throw new RuntimeException("java.lang.NullPointerException");
        float[] floats = arrRef.floats();
        if (index < 0 || index >= floats.length) throw new RuntimeException("ArrayIndexOutOfBoundsException");
        floats[index] = val;
    }
}
