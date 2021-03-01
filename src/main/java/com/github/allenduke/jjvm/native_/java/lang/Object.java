package com.github.allenduke.jjvm.native_.java.lang;

import com.github.allenduke.jjvm.native_.Registry;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.AObject;
import com.github.allenduke.jjvm.rtda.heap.Class;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/3/1
 */
public class Object {

    private static final String jlObject = "java/lang/Object";

    public static void init() {
        Registry.register(jlObject, "getClass", "()Ljava/lang/Class;", Object::getClass);
        Registry.register(jlObject, "hashCode", "()I", Object::hashCode);
        Registry.register(jlObject, "clone", "()Ljava/lang/Object;", Object::clone);
    }

    // public final native Class<?> getClass();
    // ()Ljava/lang/Class;
    private static void getClass(Frame frame) {
        AObject aThis = frame.getSlots().getThis();
        AObject jclass = aThis.getClazz().getJClass();
        frame.getOperandStack().pushRef(jclass);
    }

    // public native int hashCode();
    // ()I
    private static void hashCode(Frame frame) {
        AObject aThis = frame.getSlots().getThis();
        int hash = aThis.hashCode();    /* 这里要生成也aThis的hashcode */
        frame.getOperandStack().pushInt(hash);
    }

    // protected native Object clone() throws CloneNotSupportedException;
    // ()Ljava/lang/Object;
    private static void clone(Frame frame) {
        AObject aThis = frame.getSlots().getThis();
        Class cloneable = aThis.getClazz().getClassLoader().loadClass("java/lang/Cloneable");
        if (!aThis.getClazz().isImplements(cloneable)) {
            throw new RuntimeException("java.lang.CloneNotSupportedException");
        }
        frame.getOperandStack().pushRef(aThis.clone());
    }
}
