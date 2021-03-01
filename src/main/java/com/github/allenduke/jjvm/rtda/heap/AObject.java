package com.github.allenduke.jjvm.rtda.heap;

import com.github.allenduke.jjvm.rtda.Slot;
import com.github.allenduke.jjvm.rtda.Slots;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


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

    private Object extra;   /* 如果当前是一个java.lang.Class对象，extra指向heap.Class */

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

    public void setRefVar(String name, String descriptor, AObject ref) {
        Field field = this.clazz.getField(name, descriptor, false);
        Slots slots = (Slots) data;
        slots.setRef((int) field.getSlotId(), ref);
    }

    public AObject getRefVar(String name, String descriptor) {
        Field field = this.clazz.getField(name, descriptor, false);
        Slots slots = (Slots) data;
        return slots.getRef((int) field.getSlotId());
    }

    @Override
    public AObject clone() {
        AObject aObject = new AObject();
        aObject.clazz = this.clazz;
        aObject.data = cloneData();
        return aObject;
    }

    private Object cloneData() {
        switch (this.data.getClass().getSimpleName()) {
            case "byte[]":
                byte[] bytes = (byte[]) this.data;
                return Arrays.copyOf(bytes, bytes.length);
            case "short[]":
                short[] shorts = (short[]) this.data;
                return Arrays.copyOf(shorts, shorts.length);
            case "char[]":
                char[] chars = (char[]) this.data;
                return Arrays.copyOf(chars, chars.length);
            case "int[]":
                int[] ints = (int[]) this.data;
                return Arrays.copyOf(ints, ints.length);
            case "long[]":
                long[] longs = (long[]) this.data;
                return Arrays.copyOf(longs, longs.length);
            case "float[]":
                float[] floats = (float[]) this.data;
                return Arrays.copyOf(floats, floats.length);
            case "double[]":
                double[] doubles = (double[]) this.data;
                return Arrays.copyOf(doubles, doubles.length);
            case "AObject[]":
                AObject[] aObjects = (AObject[]) this.data;
                return Arrays.copyOf(aObjects, aObjects.length);
            case "Slots":
                Slots slots = (Slots) this.data;
                Slot[] slotArr = Arrays.copyOf(slots.getSlots(), slots.getSlots().length);
                Slots clone = new Slots(0);
                clone.setSlots(slotArr);
                return clone;
            default:
                throw new RuntimeException("array type err");
        }

    }
}
