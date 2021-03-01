package com.github.allenduke.jjvm.native_.java.lang;

import com.github.allenduke.jjvm.native_.Registry;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import com.github.allenduke.jjvm.rtda.Slots;
import com.github.allenduke.jjvm.rtda.heap.AObject;
import com.github.allenduke.jjvm.rtda.heap.ClassLoader;
import com.github.allenduke.jjvm.rtda.heap.StringPool;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/3/1
 */
public class Class {
    private static final String jlClass = "java/lang/Class";

    public static void init() {
        Registry.register(jlClass, "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;",
                Class::getPrimitiveClass);
        Registry.register(jlClass, "getName0", "()Ljava/lang/String;", Class::getName0);
        Registry.register(jlClass, "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", Class::desiredAssertionStatus0);
        //native.Register(jlClass, "isInterface", "()Z", isInterface);
        //native.Register(jlClass, "isPrimitive", "()Z", isPrimitive);
    }

    // static native Class<?> getPrimitiveClass(String name);
    // (Ljava/lang/String;)Ljava/lang/Class;
    private static void getPrimitiveClass(Frame frame) {
        AObject nameObj = frame.getSlots().getRef(0);
        String name = StringPool.String(nameObj);

        ClassLoader loader = frame.getMethod().getClazz().getClassLoader();
        AObject jClass = loader.loadClass(name).getJClass();

        frame.getOperandStack().pushRef(jClass);
    }

    // private native String getName0();
    // ()Ljava/lang/String;
    private static void getName0(Frame frame) {
        AObject aThis = frame.getSlots().getThis();
        com.github.allenduke.jjvm.rtda.heap.Class clazz = (com.github.allenduke.jjvm.rtda.heap.Class) aThis.getExtra();

        String name = clazz.getJavaName();
        AObject nameObj = StringPool.JString(clazz.getClassLoader(), name);

        frame.getOperandStack().pushRef(nameObj);
    }

    // private static native boolean desiredAssertionStatus0(Class<?> clazz);
    // (Ljava/lang/Class;)Z
    private static void desiredAssertionStatus0(Frame frame) {
        // todo
        frame.getOperandStack().pushBoolean(false);
    }

    // public native boolean isInterface();
    // ()Z
    private static void isInterface(Frame frame) {
        Slots vars = frame.getSlots();
        AObject aThis = vars.getThis();
        com.github.allenduke.jjvm.rtda.heap.Class clazz = (com.github.allenduke.jjvm.rtda.heap.Class) aThis.getExtra();

        OperandStack stack = frame.getOperandStack();
        stack.pushBoolean(clazz.isInterface());
    }

    // public native boolean isPrimitive();
    // ()Z
    private static void isPrimitive(Frame frame) {
        Slots vars = frame.getSlots();
        AObject aThis = vars.getThis();
        com.github.allenduke.jjvm.rtda.heap.Class clazz = (com.github.allenduke.jjvm.rtda.heap.Class) aThis.getExtra();

        OperandStack stack = frame.getOperandStack();
        stack.pushBoolean(clazz.isPrimitive());
    }
}
