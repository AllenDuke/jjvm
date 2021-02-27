package com.github.allenduke.jjvm.rtda.heap;

import com.github.allenduke.jjvm.classfile.constant.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public class MethodRef extends SymRef {

    private String name;

    private String descriptor;

    private Method method;

    public static MethodRef newMethodRef(ConstantPool constantPool, ConstantInfo[] constantInfos,
                                         ConstantMethodRefInfo methodRefInfo) {
        MethodRef methodRef = new MethodRef();
        methodRef.setConstantPool(constantPool);
        ConstantClassInfo classInfo = (ConstantClassInfo) constantInfos[methodRefInfo.getClassIndex()];
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantInfos[classInfo.getNameIndex()];
        methodRef.setClassName(utf8Info.parseString());

        ConstantNameAndTypeInfo nameAndTypeInfo =
                (ConstantNameAndTypeInfo) constantInfos[methodRefInfo.getNameAndTypeIndex()];

        utf8Info = (ConstantUtf8Info) constantInfos[nameAndTypeInfo.getNameIndex()];
        methodRef.setName(utf8Info.parseString());

        utf8Info = (ConstantUtf8Info) constantInfos[nameAndTypeInfo.getDescriptorIndex()];
        methodRef.setDescriptor(utf8Info.parseString());
        return methodRef;
    }

    public Method resolvedMethod() {
        if (this.method == null) {
            this.resolveMethodRef();
        }
        return this.method;
    }

    public void resolveMethodRef() {
        Class cpClazz = this.getConstantPool().getClazz();
        Class clazz = this.resolvedClass();

        if (clazz.isInterface()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }

        this.method = lookupMethod(clazz, this.name, this.descriptor);
        if (this.method == null) {
            throw new RuntimeException("java.lang.NoSuchMethodError");
        }
        if (!this.method.isAccessibleTo(cpClazz)) {
            throw new RuntimeException("java.lang.IllegalAccessError");
        }

    }

    private Method lookupMethod(Class clazz, String name, String descriptor) {
        Method method = Method.lookupMethodInClass(clazz, name, descriptor);
        if (method == null) {
            method = Method.lookupMethodInInterfaces(clazz.getInterfaces(), name, descriptor);
        }
        return method;
    }
}

