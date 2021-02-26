package com.github.allenduke.jjvm.rtda.heap;

import com.github.allenduke.jjvm.classfile.constant.ConstantClassInfo;
import com.github.allenduke.jjvm.classfile.constant.ConstantInfo;
import com.github.allenduke.jjvm.classfile.constant.ConstantUtf8Info;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public class ClassRef extends SymRef {

    public static ClassRef newClassRef(ConstantPool constantPool, ConstantInfo[] constantInfos,
                                       ConstantClassInfo classInfo) {
        ClassRef ref = new ClassRef();
        ref.setConstantPool(constantPool);
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantInfos[classInfo.getNameIndex()];
        ref.setClassName(utf8Info.parseString());
        return ref;
    }

}
