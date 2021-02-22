package com.github.allenduke.avm.rtda;

import com.github.allenduke.avm.rtda.heap.AObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.ref.Reference;

/**
 *
 */
@Setter
@Getter
@ToString
public class Slots {

    private Slot[] slots;

    public Slots(int maxLocals) {
        if (maxLocals > 0) {    /* 初始化所有槽位 */
            slots = new Slot[maxLocals];
            for (int i = 0; i < maxLocals; i++) {
                slots[i] = new Slot();
            }
        } else
            System.exit(-1);
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
}
