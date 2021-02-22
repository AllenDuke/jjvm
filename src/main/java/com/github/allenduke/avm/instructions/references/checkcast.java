package com.github.allenduke.avm.instructions.references;

import com.github.allenduke.avm.instructions.base.Index16Instruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;
import com.github.allenduke.avm.rtda.heap.AObject;
import com.github.allenduke.avm.rtda.heap.Class;
import com.github.allenduke.avm.rtda.heap.ClassRef;
import com.github.allenduke.avm.rtda.heap.ConstantPool;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/22
 */
public class checkcast extends Index16Instruction {
    @Override
    public int getOpCode() {
        return CODE_checkcast;
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        AObject ref = stack.popRef();
        stack.pushRef(ref);
        if (ref == null) {
            return;
        }

        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(index);
        Class clazz = classRef.resolvedClass();
        if (!ref.isInstanceOf(clazz))
            throw new RuntimeException("java.lang.ClassCastException");
    }
}
