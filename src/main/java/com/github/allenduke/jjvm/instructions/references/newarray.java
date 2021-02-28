package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.BytecodeReader;
import com.github.allenduke.jjvm.instructions.base.Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.AObject;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.ClassLoader;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/28
 */
public class newarray implements Instruction {

    private static final int AT_BOOLEAN = 4;
    private static final int AT_CHAR = 5;
    private static final int AT_FLOAT = 6;
    private static final int AT_DOUBLE = 7;
    private static final int AT_BYTE = 8;
    private static final int AT_SHORT = 9;
    private static final int AT_INT = 10;
    private static final int AT_LONG = 11;

    private int atype;

    private Class getPrimitiveArrayClass(ClassLoader loader, int atype) {
        switch (atype) {
            case AT_BOOLEAN:
                return loader.loadClass("[Z");
            case AT_BYTE:
                return loader.loadClass("[B");
            case AT_CHAR:
                return loader.loadClass("[C");
            case AT_SHORT:
                return loader.loadClass("[S");
            case AT_INT:
                return loader.loadClass("[I");
            case AT_LONG:
                return loader.loadClass("[J");
            case AT_FLOAT:
                return loader.loadClass("[F");
            case AT_DOUBLE:
                return loader.loadClass("[D");
            default:
                throw new RuntimeException("Invalid atype!");
        }
    }

    @Override
    public int getOpCode() {
        return CODE_newarray;
    }

    @Override
    public String getReName() {
        return "newarray";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) throws Exception {
        this.atype = reader.readUInt8();
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new RuntimeException("java.lang.NegativeArraySizeException");
        }
        ClassLoader classLoader = frame.getMethod().getClazz().getClassLoader();

        Class arrClass = getPrimitiveArrayClass(classLoader, this.atype);
        AObject arr = arrClass.newArray(count);
        stack.pushRef(arr);
    }
}
