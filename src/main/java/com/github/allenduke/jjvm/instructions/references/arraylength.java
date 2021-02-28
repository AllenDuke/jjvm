package com.github.allenduke.jjvm.instructions.references;

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
public class arraylength extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_arraylength;
    }

    @Override
    public String getReName() {
        return "arraylength";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack stack = frame.getOperandStack();
        AObject arrRef = stack.popRef();
        if (arrRef == null) {
            throw new RuntimeException("java.lang.NullPointerException");
        }
        int arrayLength = arrRef.arrayLength();
        stack.pushInt(arrayLength);
    }
}
