package com.github.allenduke.jjvm.instructions.constants.ldc;

import com.github.allenduke.jjvm.instructions.base.Index16Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.ConstantPool;

public class ldc_w extends Index16Instruction {

    @Override
    public int getOpCode() {
        return CODE_ldc_w;
    }

    @Override
    public String getReName() {
        return "ldc_w";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        Object constant = constantPool.getConstant(index);
        switch (constant.getClass().getSimpleName()) {
            case "Integer":
                stack.pushInt((Integer)constant);
                break;
            case "Float":
                stack.pushFloat((Float)constant);
                break;
            case "String":
            case "ClassRef":
            default:
                throw new RuntimeException("todo ldc_w");
        }
    }
}
