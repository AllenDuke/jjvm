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
            methods[i] = new Method();
            methods[i].clazz = clazz;
            methods[i].copyMethodInfo(methodInfos[i]);
            methods[i].copyAttributes(methodInfos[i]);
            methods[i].calcArgSlotCount();
        }
        return methods;
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
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_PUBLIC);
    }

    public boolean isProtected() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_PROTECTED);
    }

    public boolean isPrivate() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_PRIVATE);
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

    public void calcArgSlotCount() {
        MethodDescriptor parsedDescriptor = MethodDescriptorParser.parseMethodDescriptor(this.descriptor);

        parsedDescriptor.getParameterTypes().forEach(paramType -> {
            this.argSlotCount++;
            if (paramType.equals("J") || paramType.equals("D")) {
                this.argSlotCount++;
            }
        });

        if (!this.isStatic()) {
            this.argSlotCount++;
        }

    }

    public boolean isStatic() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_STATIC);
    }

    public boolean isNative() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_NATIVE);
    }

    public boolean isAbstract() {
        int flag = Integer.valueOf(accessFlags, 16);
        return 0 != (flag & AccessFlags.ACC_ABSTRACT);
    }
}
