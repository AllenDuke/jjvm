package com.github.allenduke.avm.rtda.heap;

import com.github.allenduke.avm.rtda.Slots;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public class AObject {

    private Class clazz;

    private Object data;

    private Object extra;

    public static AObject newObject(Class clazz) {
        // todo 这里应该是在堆中new
        AObject aObject = new AObject();
        aObject.clazz = clazz;
        aObject.data = new Slots((int) clazz.getInstanceSlotCount());
        return aObject;
    }

    public Slots getFields(){
        return (Slots) data;
    }

    public boolean isInstanceOf(Class clazz) {
        return clazz.IsAssignableFrom(this.clazz);
    }
}
