package com.github.allenduke.jjvm.rtda;

import com.github.allenduke.jjvm.rtda.heap.AObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Stack;

@Getter
@Setter
@ToString
public class OperandStack {     /* 每个方法的操作数栈，在编译时就以确定 */

    private Stack<Slot> stacks;

    private int maxStack;

    public OperandStack(int maxStack) {
        this.maxStack = maxStack;
        if (maxStack > 0) {
            stacks = new Stack<>();
        } else
            System.exit(-1);
    }

    public void pushSlot(Slot slot) throws RuntimeException {
        if (stacks.size() >= maxStack) {
            throw new RuntimeException("size > maxStack");
        }
        stacks.push(slot);
    }

    public Slot popSlot() throws RuntimeException {
        if (stacks.size() <= 0) {
            throw new RuntimeException("size <0 ");
        }
        return stacks.pop();
    }

    public void pushInt(int val) {
        Slot slot = new Slot();
        slot.setNum(val);
        pushSlot(slot);
    }

    public int popInt() {
        return popSlot().getNum();
    }

    public void pushFloat(float val) {
        int a = Float.floatToIntBits(val);
        pushInt(a);
    }

    public float popFloat() {
        return Float.intBitsToFloat(popInt());
    }

    public void pushLong(long val) {
        pushInt((int) (val >> 32));     /* 大端，高位在低 */
        pushInt((int) val);
    }

    public long popLong() {
        long ll = popInt();             /* 先pop出来的是低位 */
        long lh = popInt();
        return (lh << 32) | ll;
    }

    public void pushDouble(double val) {
        pushLong(Double.doubleToLongBits(val));
    }

    public double popDouble() {
        return Double.longBitsToDouble(popLong());
    }

    public void pushRef(AObject ref) {
        Slot slot = new Slot();
        slot.setRef(ref);
        pushSlot(slot);
    }

    public AObject popRef() {
        return popSlot().getRef();
    }

    public AObject getRefFromTop(int i) {
        return stacks.get(stacks.size() - 1 - i).getRef();
    }
}
