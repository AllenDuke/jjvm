package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.Index16Instruction;
import com.github.allenduke.jjvm.instructions.base.MethodInvokeLogic;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.*;

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
        Class curClazz = frame.getMethod().getClazz();
        ConstantPool constantPool = curClazz.getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        Method resolvedMethod = methodRef.resolvedMethod();

        if (resolvedMethod.isStatic()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }

        AObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);

        if (ref == null) {
            // hack!
            if (methodRef.getName().equals("println")) {
                println(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }

            throw new RuntimeException("java.lang.NullPointerException");
        }

        if (resolvedMethod.isProtected() && resolvedMethod.getClazz().isSuperClassOf(curClazz) &&
                !resolvedMethod.getClazz().getPackageName().equals(curClazz.getPackageName()) &&
                ref.getClazz() != curClazz && !ref.getClazz().isSubClassOf(curClazz)) {
            throw new RuntimeException("java.lang.IllegalAccessError");
        }

        Method methodToBeInvoked=Method.lookupMethodInClass(ref.getClazz(),
                methodRef.getName(), methodRef.getDescriptor());
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new RuntimeException("java.lang.AbstractMethodError");
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }

    private void println(OperandStack stack, String descriptor) {
        switch (descriptor) {
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
                throw new RuntimeException("println " + descriptor);
        }
    }
}
