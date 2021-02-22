package com.github.allenduke.avm.instructions.references;

import com.github.allenduke.avm.instructions.base.Index16Instruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;
import com.github.allenduke.avm.rtda.Slots;
import com.github.allenduke.avm.rtda.heap.*;
import com.github.allenduke.avm.rtda.heap.Class;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/22
 */
public class putfield extends Index16Instruction {
    @Override
    public int getOpCode() {
        return CODE_putfield;
    }

    @Override
    public void execute(Frame frame) {
        Method curMethod = frame.getMethod();
        Class curClass = curMethod.getClazz();
        ConstantPool constantPool = curClass.getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(index);
        Field field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }

        /* 如果是static final那么该静态常量只能在类初始化方法中给它赋值，类的初始化方法<clinit>由编译器生成 */
        if (field.isFinal()) {
            if (curClass != field.getClazz() || !curMethod.getName().equals("<init>")) {
                throw new RuntimeException("java.lang.IllegalAccessError");
            }
        }

        String descriptor = field.getDescriptor();
        int slotId = (int) field.getSlotId();
        OperandStack stack = frame.getOperandStack();

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                int i = stack.popInt();
                AObject ref = stack.popRef();
                if (ref == null) {
                    throw new RuntimeException("java.lang.NullPointerException");
                }
                ref.getFields().setInt(slotId, i);
            case 'F':
                float f = stack.popFloat();
                ref = stack.popRef();
                if (ref == null) {
                    throw new RuntimeException("java.lang.NullPointerException");
                }
                ref.getFields().setFloat(slotId, f);
            case 'J':
                long l = stack.popLong();
                ref = stack.popRef();
                if (ref == null) {
                    throw new RuntimeException("java.lang.NullPointerException");
                }
                ref.getFields().setLong(slotId, l);
            case 'D':
                double d = stack.popDouble();
                ref = stack.popRef();
                if (ref == null) {
                    throw new RuntimeException("java.lang.NullPointerException");
                }
                ref.getFields().setDouble(slotId, d);
            case 'L':
            case '[':
                AObject r = stack.popRef();
                ref = stack.popRef();
                if (ref == null) {
                    throw new RuntimeException("java.lang.NullPointerException");
                }
                ref.getFields().setRef(slotId, r);
            default:
                // todo
        }
    }
}
