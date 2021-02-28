package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.Index16Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.AObject;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.ClassRef;
import com.github.allenduke.jjvm.rtda.heap.ConstantPool;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/28
 */
public class anewarray extends Index16Instruction {
    @Override
    public int getOpCode() {
        return CODE_anewarray;
    }

    @Override
    public String getReName() {
        return "anewarray";
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        Class componentClass = classRef.resolvedClass();

        // if componentClass.InitializationNotStarted() {
        // 	thread := frame.Thread()
        // 	frame.SetNextPC(thread.PC()) // undo anewarray
        // 	thread.InitClass(componentClass)
        // 	return
        // }

        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new RuntimeException("java.lang.NegativeArraySizeException");
        }

        Class arrClass = componentClass.arrayClass();
        AObject arr = arrClass.newArray(count);
        stack.pushRef(arr);
    }
}
