package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.ClassInitLogic;
import com.github.allenduke.jjvm.instructions.base.Index16Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.Slots;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.ConstantPool;
import com.github.allenduke.jjvm.rtda.heap.Field;
import com.github.allenduke.jjvm.rtda.heap.FieldRef;

/**
 * @author allen
 * @description 取出类的某个静态变量值，压入操作数栈
 * @contact AllenDuke@163.com
 * @date 2021/2/22
 */
public class getstatic extends Index16Instruction {
    @Override
    public int getOpCode() {
        return CODE_getstatic;
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(index);
        Field field = fieldRef.resolvedField();
        Class clazz = field.getClazz();

        if (!clazz.isInitStarted()) {
            frame.revertNextPc();
            ClassInitLogic.initClass(frame.getJthread(), clazz);
            return;
        }

        if (!field.isStatic()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }

        String descriptor = field.getDescriptor();
        int slotId = (int) field.getSlotId();
        Slots slots = clazz.getStaticVars();
        OperandStack stack = frame.getOperandStack();

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                stack.pushInt(slots.getInt(slotId));
                break;
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case 'J':
                stack.pushLong(slots.getLong(slotId));
                break;
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case 'L':
            case '[':
                stack.pushRef(slots.getRef(slotId));
                break;
            default:
                // todo
        }
    }
}
