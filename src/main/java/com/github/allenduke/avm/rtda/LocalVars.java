package com.github.allenduke.avm.rtda;

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
public class LocalVars {

    private Slot[] localVars;

    public LocalVars(int maxLocals) {
        if (maxLocals > 0) {    /* 初始化所有槽位 */
            localVars = new Slot[maxLocals];
            for (int i = 0; i < maxLocals; i++) {
                localVars[i] = new Slot();
            }
        } else
            System.exit(-1);
    }

    public void setInt(int index, int val) {
        localVars[index].setNum(val);
    }

    public int getInt(int index) {
        Slot slot = localVars[index];
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
        localVars[index].setNum((int) (val >> 32));  /* 大端，高位在低 */
        localVars[index + 1].setNum((int) val);
    }

    public long getLong(int index) {
        long lh = localVars[index].getNum();
        long ll = localVars[index + 1].getNum();
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

    public void setRef(int index, Object ref) {
        localVars[index].setRef(ref);
    }

    public Object getRef(int index) {
        return localVars[index].getRef();
    }
}
