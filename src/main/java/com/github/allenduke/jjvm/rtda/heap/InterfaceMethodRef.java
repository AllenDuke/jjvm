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
public class InterfaceMethodRef extends SymRef {

    private String name;

    private String descriptor;

    private Method method;

    public static InterfaceMethodRef newInterfaceMethodRef(ConstantPool constantPool, ConstantInfo[] constantInfos,
                                                           ConstantInterfaceMethodRefInfo methodRefInfo) {
        InterfaceMethodRef methodRef = new InterfaceMethodRef();
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

    public Method resolvedInterfaceMethod() {
        if (this.method == null) {
            this.resolvedInterfaceMethodRef();
        }
        return this.method;
    }

    public void resolvedInterfaceMethodRef() {
        Class cpClazz = this.getConstantPool().getClazz();
        Class clazz = this.resolvedClass();

        if (!clazz.isInterface()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }

        this.method = lookupInterfaceMethod(clazz, this.name, this.descriptor);
        if (this.method == null) {
            throw new RuntimeException("java.lang.NoSuchMethodError");
        }
        if (!this.method.isAccessibleTo(cpClazz)) {
            throw new RuntimeException("java.lang.IllegalAccessError");
        }

    }

    private Method lookupInterfaceMethod(Class iface, String name, String descriptor) {
        for (Method method : iface.getMethods()) {
            if (method.getName().equals(name) && method.getDescriptor().equals(descriptor))
                return method;
        }
        return Method.lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
    }

}
