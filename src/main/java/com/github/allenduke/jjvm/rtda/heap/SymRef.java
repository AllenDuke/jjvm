package com.github.allenduke.jjvm.rtda.heap;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * @author allen
 * @description 符号引用，在某一类的方法中，某一符号引用引用了其他类
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public abstract class SymRef {

    private ConstantPool constantPool;

    private String className;   /* 该符号引用所引用的类的全限定名 */

    private Class clazz;        /* 该符号引用所引用的类 */

    public Class resolvedClass() {
        if (clazz == null) {
            resolvedClassRef();
        }
        return clazz;
    }

    private void resolvedClassRef() {
        Class cpClazz = constantPool.getClazz();    /* 该符号引用所在的类 */
        clazz = cpClazz.getClassLoader().loadClass(className);
        /* cpClazz要访问clazz */
        if (!clazz.isAccessibleTo(cpClazz)) {
            throw new RuntimeException("java.lang.IllegalAccessError");
        }
    }
}
