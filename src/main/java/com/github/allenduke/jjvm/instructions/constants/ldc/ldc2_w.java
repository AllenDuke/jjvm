package com.github.allenduke.jjvm.instructions.constants.ldc;

import com.github.allenduke.jjvm.instructions.base.Index16Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.ConstantPool;

public class ldc2_w extends Index16Instruction {

    @Override
    public int getOpCode() {
        return CODE_ldc2_w;
    }

    @Override
    public String getReName() {
        return "ldc2_w";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        Object constant = constantPool.getConstant(index);
        switch (constant.getClass().getSimpleName()) {
            case "Long":
                stack.pushLong((Long) constant);
                break;
            case "Double":
                stack.pushDouble((Double) constant);
                break;
            default:
                throw new RuntimeException("java.lang.ClassFormatError");
        }
    }
}
