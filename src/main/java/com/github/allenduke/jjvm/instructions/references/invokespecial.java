package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.Index16Instruction;
import com.github.allenduke.jjvm.instructions.base.MethodInvokeLogic;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.*;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/22
 */
public class invokespecial extends Index16Instruction {
    @Override
    public int getOpCode() {
        return CODE_invokespecial;
    }

    @Override
    public void execute(Frame frame) {
        Class curClazz = frame.getMethod().getClazz();
        ConstantPool constantPool = curClazz.getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        Class resolvedClass = methodRef.resolvedClass();
        Method resolvedMethod = methodRef.resolvedMethod();

        if (resolvedMethod.getName().equals("<init>") && !resolvedMethod.getClazz().equals(resolvedClass)) {
            throw new RuntimeException("java.lang.NoSuchMethodError");
        }
        if (resolvedMethod.isStatic()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }

        AObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            throw new RuntimeException("java.lang.NullPointerException");
        }

        if (resolvedMethod.isProtected() && resolvedMethod.getClazz().isSuperClassOf(curClazz) &&
                !resolvedMethod.getClazz().getPackageName().equals(curClazz.getPackageName()) &&
                ref.getClazz() != curClazz && !ref.getClazz().isSubClassOf(curClazz)) {
            throw new RuntimeException("java.lang.IllegalAccessError");
        }

        Method methodToBeInvoked = resolvedMethod;
        if (curClazz.isSuper() && resolvedClass.isSuperClassOf(curClazz)
                && !resolvedMethod.getName().equals("<init>")) {
            methodToBeInvoked = Method.lookupMethodInClass(curClazz.getSuperClass(),
                    methodRef.getName(), methodRef.getDescriptor());
        }

        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new RuntimeException("java.lang.AbstractMethodError");
        }
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
}
