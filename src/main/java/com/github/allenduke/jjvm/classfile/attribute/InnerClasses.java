package com.github.allenduke.jjvm.classfile.attribute;

import com.github.allenduke.jjvm.classfile.ClassFile;
import com.github.allenduke.jjvm.classfile.constant.ConstantClassInfo;
import com.github.allenduke.jjvm.classfile.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnerClasses extends AttributeInfo {

    private int number;

    private Clazz[] classes;

    @Override
    public String getName() {
        return "InnerClasses";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        if (!getName().equals(getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        setIndex(0);
        setNumber(read(2));
        Clazz[] clazzes = new Clazz[getNumber()];
        for (int i = 0; i < clazzes.length; i++) {
            Clazz clazz = new Clazz();
            ConstantClassInfo inner = (ConstantClassInfo) classFile.getConstantPool()[read(2)];
            clazz.setInner_class_info(inner);
            ConstantClassInfo outer = (ConstantClassInfo) classFile.getConstantPool()[read(2)];
            clazz.setOuter_class_info(outer);
            int read = read(2);
            if (read == 0)
                clazz.setInner_name(null);
            else {
                ConstantUtf8Info inner_name = (ConstantUtf8Info) classFile.getConstantPool()[read];
                clazz.setInner_name(inner_name);
            }
            clazz.setInner_class_access_flag(read(2));
            clazzes[i] = clazz;
        }
        setClasses(clazzes);
        return this;
    }

    @Getter
    @Setter
    class Clazz {

        private ConstantClassInfo inner_class_info;

        private ConstantClassInfo outer_class_info;

        private ConstantUtf8Info inner_name;

        private int inner_class_access_flag;

    }
}
