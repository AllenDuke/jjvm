package com.github.allenduke.avm.instructions.constants.ldc;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;
import com.github.allenduke.avm.rtda.heap.ConstantPool;

/* ldc系列指令从常量池中加载常量，压入操作数栈 */
public class ldc extends Index8Instruction {

    @Override
    public int getOpCode() {
        return CODE_ldc;
    }

    @Override
    public String getReName() {
        return "ldc";
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
                throw new RuntimeException("todo ldc");
        }
    }
}
