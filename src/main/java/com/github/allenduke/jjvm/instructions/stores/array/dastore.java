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
public class dastore extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_dastore;
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double val = stack.popDouble();
        int index = stack.popInt();
        AObject arrRef = stack.popRef();

        if (arrRef == null) throw new RuntimeException("java.lang.NullPointerException");
        double[] doubles = arrRef.doubles();
        if (index < 0 || index >= doubles.length) throw new RuntimeException("ArrayIndexOutOfBoundsException");
        doubles[index] = val;
    }
}
