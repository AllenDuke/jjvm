package com.github.allenduke.jjvm.instructions.base;

import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.JThread;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.Method;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/27
 */
public class ClassInitLogic {

    public static void initClass(JThread jThread, Class clazz) {
        clazz.startInit();
        scheduleClinit(jThread, clazz);
        initSuperClass(jThread, clazz);
    }

    private static void scheduleClinit(JThread jThread, Class clazz) {
        Method clinit = clazz.getClinitMethod();
        if (clinit != null) {
            // exec <clinit>
            Frame newFrame = new Frame(jThread, (int) clinit.getMaxLocals(), (int) clinit.getMaxStack(), clinit);
            jThread.pushFrame(newFrame);
        }
    }

    private static void initSuperClass(JThread jThread, Class clazz) {
        if (!clazz.isInterface()) {
            Class superClass = clazz.getSuperClass();
            if (superClass != null && !superClass.isInitStarted()) {
                initClass(jThread, superClass);
            }
        }
    }
}
