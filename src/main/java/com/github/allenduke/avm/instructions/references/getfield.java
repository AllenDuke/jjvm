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
public class getfield extends Index16Instruction {
    @Override
    public int getOpCode() {
        return CODE_getfield;
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(index);
        Field field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }

        OperandStack stack = frame.getOperandStack();
        AObject ref = stack.popRef();
        if (ref == null) {
            throw new RuntimeException("java.lang.NullPointerException");
        }

        String descriptor = field.getDescriptor();
        int slotId = (int) field.getSlotId();
        Slots slots = ref.getFields();

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
