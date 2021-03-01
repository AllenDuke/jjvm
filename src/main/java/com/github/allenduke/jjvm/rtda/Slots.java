package com.github.allenduke.jjvm.rtda;

import com.github.allenduke.jjvm.rtda.heap.AObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 */
@Setter
@Getter
@ToString
public class Slots {

    private Slot[] slots;

    public Slots(int maxLocals) {
        /* 初始化所有槽位 */
        slots = new Slot[maxLocals];
        for (int i = 0; i < maxLocals; i++) {
            slots[i] = new Slot();
        }
    }

    public void setInt(int index, int val) {
        slots[index].setNum(val);
    }

    public int getInt(int index) {
        Slot slot = slots[index];
        return slot.getNum();
    }

    public void setFloat(int index, float val) {
        int a = Float.floatToIntBits(val);
        setInt(index, a);
    }

    public float getFloat(int index) {
        int a = getInt(index);
        return Float.intBitsToFloat(a);
    }

    public void setLong(int index, long val) {
        slots[index].setNum((int) (val >> 32));  /* 大端，高位在低 */
        slots[index + 1].setNum((int) val);
    }

    public long getLong(int index) {
        long lh = slots[index].getNum();
        long ll = slots[index + 1].getNum();
        return (lh << 32) | ll;
    }

    public void setDouble(int index, double val) {
        long v = Double.doubleToLongBits(val);
        setLong(index, v);
    }

    public double getDouble(int index) {
        long v = getLong(index);
        return Double.longBitsToDouble(v);
    }

    public void setRef(int index, AObject ref) {
        slots[index].setRef(ref);
    }

    public AObject getRef(int index) {
        return slots[index].getRef();
    }

    public void setSlot(int i, Slot slot) {
        this.slots[i] = slot;
    }

    public AObject getThis(){
        return getRef(0);
    }
}
