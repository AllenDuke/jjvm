package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.ClassInitLogic;
import com.github.allenduke.jjvm.instructions.base.Index16Instruction;
import com.github.allenduke.jjvm.instructions.base.MethodInvokeLogic;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.ConstantPool;
import com.github.allenduke.jjvm.rtda.heap.Method;
import com.github.allenduke.jjvm.rtda.heap.MethodRef;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/27
 */
public class invokestatic extends Index16Instruction {
    @Override
    public int getOpCode() {
        return CODE_invokestatic;
    }

    @Override
    public String getReName() {
        return "invokestatic";
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        Method method = methodRef.resolvedMethod();

        if (!method.isStatic()) {
            throw new RuntimeException("java.lang.IncompatibleChangeError");
        }

        Class clazz = method.getClazz();
        if (!clazz.isInitStarted()) {
            frame.revertNextPc();
            ClassInitLogic.initClass(frame.getJthread(), clazz);
            return;
        }

        MethodInvokeLogic.invokeMethod(frame, method);
    }
}
