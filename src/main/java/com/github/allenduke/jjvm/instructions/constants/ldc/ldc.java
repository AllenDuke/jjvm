package com.github.allenduke.jjvm.instructions.constants.ldc;

import com.github.allenduke.jjvm.instructions.base.Index8Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.*;
import com.github.allenduke.jjvm.rtda.heap.Class;

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
        Class clazz = frame.getMethod().getClazz();
        ConstantPool constantPool = clazz.getConstantPool();
        Object constant = constantPool.getConstant(index);
        switch (constant.getClass().getSimpleName()) {
            case "Integer":
                stack.pushInt((Integer) constant);
                break;
            case "Float":
                stack.pushFloat((Float) constant);
                break;
            case "String":
                AObject internedStr = StringPool.JString(clazz.getClassLoader(), (String) constant);
                stack.pushRef(internedStr);
                break;
            case "ClassRef":
                ClassRef classRef = (ClassRef) constant;
                AObject jClassObject = classRef.resolvedClass().getJClass();
                stack.pushRef(jClassObject);
            default:
                throw new RuntimeException("todo ldc");
        }
    }
}
