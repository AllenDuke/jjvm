package com.github.allenduke.avm.instructions.references;

import com.github.allenduke.avm.instructions.base.Index16Instruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;
import com.github.allenduke.avm.rtda.Slots;
import com.github.allenduke.avm.rtda.heap.Class;
import com.github.allenduke.avm.rtda.heap.ConstantPool;
import com.github.allenduke.avm.rtda.heap.Field;
import com.github.allenduke.avm.rtda.heap.FieldRef;

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
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
            case 'J':
                stack.pushLong(slots.getLong(slotId));
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
            case 'L':
            case '[':
                stack.pushRef(slots.getRef(slotId));
            default:
                // todo
        }
    }
}
