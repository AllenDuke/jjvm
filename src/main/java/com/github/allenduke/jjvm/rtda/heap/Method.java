package com.github.allenduke.jjvm.rtda.heap;

import com.github.allenduke.jjvm.classfile.attribute.CodeAttribute;
import com.github.allenduke.jjvm.classfile.method.MethodInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/19
 */
public class Method {

    private int accessFlags;        /* u2 */

    private String name;

    private String descriptor;

    private String signature;

    private byte[] annotationData;  // RuntimeVisibleAnnotations_attribute

    private Class clazz;

    private long maxStack;

    private long maxLocals;

    private byte[] code;

    private int argSlotCount;

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
            methods[i] = newMethod(clazz, methodInfos[i]);
        }
        return methods;
    }

    private static Method newMethod(Class clazz, MethodInfo methodInfo) {
        Method method = new Method();
        method.clazz = clazz;
        method.copyMethodInfo(methodInfo);
        method.copyAttributes(methodInfo);
        MethodDescriptor md = MethodDescriptorParser.parseMethodDescriptor(method.descriptor);
        method.calcArgSlotCount(md.getParameterTypes());
        if (method.isNative()) {
            method.injectCodeAttribute(md.getReturnType());
        }
        return method;
    }

    private void injectCodeAttribute(String returnType) {
        this.maxStack = 4; // todo
        this.maxLocals = this.argSlotCount;
        switch (returnType.charAt(0)) {
            case 'V':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb1}; // return
                break;
            case 'L':
            case '[':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb0}; // areturn
                break;
            case 'D':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xaf}; // dreturn
                break;
            case 'F':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xae}; // freturn
                break;
            case 'J':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xad}; // lreturn
                break;
            default:
                this.code = new byte[]{(byte) 0xfe, (byte) 0xac}; // ireturn
                break;
        }
    }

    public static Method lookupMethodInClass(Class clazz, String name, String descriptor) {
        Class cur = clazz;
        while (cur != null) {
            for (Method method : cur.getMethods()) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor))
                    return method;
            }
            cur = cur.getSuperClass();
        }
        return null;
    }

    public static Method lookupMethodInInterfaces(Class[] ifaces, String name, String descriptor) {
        for (Class iface : ifaces) {
            for (Method method : iface.getMethods()) {
                if (method.name.equals(name) && method.getDescriptor().equals(descriptor))
                    return method;
            }
            Method method = lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
            if (method != null) return method;
        }
        return null;
    }

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isProtected() {
        return 0 != (accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isAccessibleTo(Class other) {
        if (isPublic()) return true;
        if (isProtected()) {
            return other == clazz || other.isSubClassOf(clazz) || clazz.getPackageName().equals(other.getPackageName());
        }
        if (!isPrivate()) {
            return clazz.getPackageName().equals(other.getPackageName());
        }
        return clazz == other;
    }

    public void calcArgSlotCount(List<String> paramTypes) {
        for (String paramType : paramTypes) {
            this.argSlotCount++;
            if (paramType.equals("J") || paramType.equals("D")) {
                this.argSlotCount++;
            }
        }

        if (!this.isStatic()) {
            this.argSlotCount++;
        }

    }

    public boolean isStatic() {
        return 0 != (accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isNative() {
        return 0 != (accessFlags & AccessFlags.ACC_NATIVE);
    }

    public boolean isAbstract() {
        return 0 != (accessFlags & AccessFlags.ACC_ABSTRACT);
    }
}
