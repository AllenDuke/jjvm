package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.BytecodeReader;
import com.github.allenduke.jjvm.instructions.base.Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.heap.AObject;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.ClassRef;
import com.github.allenduke.jjvm.rtda.heap.ConstantPool;

import java.util.Arrays;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/28
 */
public class multianewarray implements Instruction {

    private int index;          /* u2 */

    private int dimensions;     /* u1 */

    @Override
    public int getOpCode() {
        return CODE_multianewarray;
    }

    @Override
    public String getReName() {
        return "multianewarray";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) throws Exception {
        this.index = reader.readInt16();
        this.index = reader.readUInt8();
    }

    @Override
    public void execute(Frame frame) throws Exception {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        Class arrClass = classRef.resolvedClass();

        OperandStack stack = frame.getOperandStack();
        int[] counts = popAndCheckCounts(stack, this.dimensions);
        AObject arr = newMultiDimensionalArray(counts, arrClass);
        stack.pushRef(arr);
    }

    private int[] popAndCheckCounts(OperandStack stack, int dimensions) {
        int[] counts = new int[dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            counts[i] = stack.popInt();
            if (counts[i] < 0) {
                throw new RuntimeException("java.lang.NegativeArraySizeException");
            }
        }
        return counts;
    }

    private AObject newMultiDimensionalArray(int[] counts, Class arrClass) {
        int count = counts[0];
        AObject arr = arrClass.newArray(count);
        if (counts.length > 1) {
            AObject[] refs = arr.refs();
            for (int i = 0; i < refs.length; i++) {
                refs[i] = newMultiDimensionalArray(Arrays.copyOfRange(counts, 1, counts.length),
                        arrClass.getComponentClass());
            }
        }

        return arr;
    }
}
