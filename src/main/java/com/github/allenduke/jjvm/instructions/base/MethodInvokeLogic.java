package com.github.allenduke.jjvm.instructions.base;

import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.JThread;
import com.github.allenduke.jjvm.rtda.Slot;
import com.github.allenduke.jjvm.rtda.heap.Method;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/26
 */
public class MethodInvokeLogic {

    public static void invokeMethod(Frame invokerFrame, Method method) {
        JThread jthread = invokerFrame.getJthread();
        Frame newFrame = new Frame(jthread, (int) method.getMaxLocals(),(int) method.getMaxStack(), method);
        jthread.pushFrame(newFrame);

        int argSlotCount = method.getArgSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getSlots().setSlot(i, slot);
            }
        }

//        if (method.isNative()) {
//            if (method.getName().equals("registerNatives")) {
//                jthread.popFrame();
//            } else {
//                throw new RuntimeException("native method:" + method.getClazz().getName() + " " + method.getName() +
//                        " " + method.getDescriptor());
//            }
//        }
    }


}
