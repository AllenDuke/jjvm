package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.Index16Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.Slots;
import com.github.allenduke.jjvm.rtda.heap.*;
import com.github.allenduke.jjvm.rtda.heap.Class;

/**
 * @author allen
 * @description 给类的某个静态变量赋值，它需要两个操作数，第一个操作数是uint16索引，来自字节码。通过这个索引可以从
 * 当前类的运行时常量池中找到一个字段符号引用，解析这个符号引用就可以知道要给类的哪个静态变量赋值。第二个操作数是要赋给
 * 静态变量的值，从操作数栈中弹出。
 * @contact AllenDuke@163.com
 * @date 2021/2/22
 */
public class putstatic extends Index16Instruction {
    @Override
    public int getOpCode() {
        return CODE_putstatic;
    }

    @Override
    public void execute(Frame frame) {
        Method curMethod = frame.getMethod();
        Class curClass = curMethod.getClazz();
        ConstantPool constantPool = curClass.getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(index);
        Field field = fieldRef.resolvedField();
        Class clazz = field.getClazz();

        // todo 如果类还没有初始化，需要先初始化该类

        if (!field.isStatic()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }

        /* 如果是static final那么该静态常量只能在类初始化方法中给它赋值，类的初始化方法<clinit>由编译器生成 */
        if (field.isFinal()) {
            if (curClass != clazz || !curMethod.getName().equals("<clinit>")) {
                throw new RuntimeException("java.lang.IllegalAccessError");
            }
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
                slots.setInt(slotId, stack.popInt());
                break;
            case 'F':
                slots.setFloat(slotId, stack.popFloat());
                break;
            case 'J':
                slots.setLong(slotId, stack.popLong());
                break;
            case 'D':
                slots.setDouble(slotId, stack.popDouble());
                break;
            case 'L':
            case '[':
                slots.setRef(slotId, stack.popRef());
                break;
            default:
                // todo
        }
    }
}
