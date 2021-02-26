package com.github.allenduke.jjvm.rtda.heap;

import com.github.allenduke.jjvm.classfile.attribute.CodeAttribute;
import com.github.allenduke.jjvm.classfile.method.MethodInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/19
 */
public class Method {

    private String accessFlags;

    private String name;

    private String descriptor;

    private String signature;

    private byte[] annotationData; // RuntimeVisibleAnnotations_attribute

    private Class clazz;

    private long maxStack;

    private long maxLocals;

    private byte[] code;

    public void copyMethodInfo(MethodInfo info) {
        this.accessFlags = info.getAccessFlags();
        this.name = info.getName();
        this.descriptor = info.getDescriptor();
    }

    public void copyAttributes(MethodInfo methodInfo) {
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if (codeAttribute != null) {
            this.maxStack = codeAttribute.getMaxStack();
            this.maxLocals = codeAttribute.getMaxLocals();
            this.code = codeAttribute.getCode();
        }
    }

    public static Method[] newMethods(Class clazz, MethodInfo[] methodInfos) {
        Method[] methods = new Method[methodInfos.length];
        for (int i = 0; i < methods.length; i++) {
            methods[i] = new Method();
            methods[i].clazz = clazz;
            methods[i].copyMethodInfo(methodInfos[i]);
            methods[i].copyAttributes(methodInfos[i]);
        }
        return methods;
    }
}
