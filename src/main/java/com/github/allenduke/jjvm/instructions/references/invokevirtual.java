package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.Index16Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.ConstantPool;
import com.github.allenduke.jjvm.rtda.heap.MethodRef;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/22
 */
public class invokevirtual extends Index16Instruction {
    @Override
    public int getOpCode() {
        return CODE_invokevirtual;
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        if (methodRef.getName().equals("println")) {
            OperandStack stack = frame.getOperandStack();
            switch (methodRef.getDescriptor()) {
                case "(Z)V":
                    System.out.println(stack.popInt() != 0);
                    break;
                case "(C)V":
                    System.out.println(stack.popInt());
                    break;
                case "(B)V":
                    System.out.println(stack.popInt());
                    break;
                case "(S)V":
                    System.out.println(stack.popInt());
                    break;
                case "(I)V":
                    System.out.println(stack.popInt());
                    break;
                case "(J)V":
                    System.out.println(stack.popLong());
                    break;
                case "(F)V":
                    System.out.println(stack.popFloat());
                    break;
                case "(D)V":
                    System.out.println(stack.popDouble());
                    break;
                default:
                    throw new RuntimeException("println " + methodRef.getDescriptor());
            }
        }
    }
}
