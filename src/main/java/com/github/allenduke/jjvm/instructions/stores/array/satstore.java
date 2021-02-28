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
public class satstore extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_sastore;
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int val = stack.popInt();
        int index = stack.popInt();
        AObject arrRef = stack.popRef();

        if (arrRef == null) throw new RuntimeException("java.lang.NullPointerException");
        short[] shorts = arrRef.shorts();
        if (index < 0 || index >= shorts.length) throw new RuntimeException("ArrayIndexOutOfBoundsException");
        shorts[index] = (short) val;
    }
}
