package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.BytecodeReader;
import com.github.allenduke.jjvm.instructions.base.Instruction;
import com.github.allenduke.jjvm.instructions.base.MethodInvokeLogic;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.*;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/27
 */
public class invokeinterface implements Instruction {

    private int index;

    @Override
    public int getOpCode() {
        return CODE_invokeinterface;
    }

    @Override
    public String getReName() {
        return "invokeinterface";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) throws Exception {
        this.index = reader.readUInt16();
        reader.readUInt8(); // count
        reader.readUInt8(); // must be 0
    }

    @Override
    public void execute(Frame frame) throws Exception {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        InterfaceMethodRef methodRef = (InterfaceMethodRef) constantPool.getConstant(index);
        Method resolvedMethod = methodRef.resolvedInterfaceMethod();
        if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }

        AObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);

        if (ref == null) {
            throw new RuntimeException("java.lang.NullPointerException"); // todo
        }
        if (!ref.getClazz().isImplements(methodRef.resolvedClass())) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }

        Method methodToBeInvoked = Method.lookupMethodInClass(ref.getClazz(), methodRef.getName(),
                methodRef.getDescriptor());
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new RuntimeException("java.lang.AbstractMethodError");
        }
        if (!methodToBeInvoked.isPublic()) {
            throw new RuntimeException("java.lang.IllegalAccessError");
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
}
