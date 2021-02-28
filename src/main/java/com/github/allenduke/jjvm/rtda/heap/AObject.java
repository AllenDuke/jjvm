package com.github.allenduke.jjvm.rtda.heap;

import com.github.allenduke.jjvm.rtda.Slots;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public class AObject {

    private long markWord;  /* 64bit vm */

    private Class clazz;

    private Object data;

    private Object extra;

    public static AObject newObject(Class clazz) {
        // todo 通过native，用c语言在堆中new
        AObject aObject = new AObject();
        aObject.clazz = clazz;
        aObject.data = new Slots((int) clazz.getInstanceSlotCount());
        return aObject;
    }

    public Slots getFields() {
        return (Slots) data;
    }

    public boolean isInstanceOf(Class clazz) {
        return clazz.IsAssignableFrom(this.clazz);
    }

    public byte[] bytes() {
        return (byte[]) this.data;
    }

    public short[] shorts() {
        return (short[]) this.data;
    }

    public char[] chars() {
        return (char[]) this.data;
    }

    public int[] ints() {
        return (int[]) this.data;
    }

    public long[] longs() {
        return (long[]) this.data;
    }

    public float[] floats() {
        return (float[]) this.data;
    }

    public double[] doubles() {
        return (double[]) this.data;
    }

    public AObject[] refs() {
        return (AObject[]) this.data;
    }

    public int arrayLength() {
        switch (this.data.getClass().getSimpleName()) {
            case "byte[]":
                return ((byte[]) this.data).length;
            case "short[]":
                return ((short[]) this.data).length;
            case "char[]":
                return ((char[]) this.data).length;
            case "int[]":
                return ((int[]) this.data).length;
            case "long[]":
                return ((long[]) this.data).length;
            case "float[]":
                return ((float[]) this.data).length;
            case "double[]":
                return ((double[]) this.data).length;
            case "AObject[]":
                return ((AObject[]) this.data).length;
            default:
                throw new RuntimeException("Not array!");
        }
    }
}
