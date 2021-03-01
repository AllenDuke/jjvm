package com.github.allenduke.jjvm.native_;

import java.util.HashMap;
import java.util.Map;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/3/1
 */
public class Registry {

    public static final Map<String,NativeMethod> REGISTRY=new HashMap<>();

    //todo 优化查找

    public static void register(String className,String methodName,String methodDescriptor,NativeMethod method) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        REGISTRY.put(key,method);
    }

    public static NativeMethod findNativeMethod(String className,String methodName,String methodDescriptor){
        String key = className + "~" + methodName + "~" + methodDescriptor;
        NativeMethod nativeMethod = REGISTRY.get(key);
        if(nativeMethod!=null) return nativeMethod;
        if (methodDescriptor.equals("()V") && methodName.equals( "registerNatives")) {
            return new EmptyNativeMethod();
        }
        return null;
    }
}
