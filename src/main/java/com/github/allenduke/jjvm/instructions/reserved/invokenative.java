package com.github.allenduke.jjvm.instructions.reserved;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.native_.NativeMethod;
import com.github.allenduke.jjvm.native_.Registry;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.Method;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/3/1
 */
public class invokenative extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_invokenative;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        Method method = frame.getMethod();
        String className = method.getClazz().getName();
        String methodName = method.getName();
        String methodDescriptor = method.getDescriptor();

        NativeMethod nativeMethod = Registry.findNativeMethod(className, methodName, methodDescriptor);
        if (nativeMethod == null) {
            String methodInfo = className + "." + methodName + methodDescriptor;
            throw new RuntimeException("java.lang.UnsatisfiedLinkError: " + methodInfo);
        }
        nativeMethod.invoke(frame);
    }
}
