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
}
