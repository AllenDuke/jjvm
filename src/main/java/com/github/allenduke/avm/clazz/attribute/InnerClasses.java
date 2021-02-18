package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import com.github.allenduke.avm.clazz.ClassReader;
import com.github.allenduke.avm.clazz.constant.ConstantClassInfo;
import com.github.allenduke.avm.clazz.constant.ConstantUtf8Info;
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
        if (!getName().equals(getAttribute_name())) {
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
            byte[] bytes = readBytes(2);
            String s = ClassReader.toHexString(bytes);
            if (s.length() < 4) {
                for (; 0 < 4 - s.length(); ) {
                    s = "0" + s;
                }
            }
            clazz.setInner_class_access_flag(parseAccessFlag(s));
            clazzes[i] = clazz;
        }
        setClasses(clazzes);
        return this;
    }


    private String parseAccessFlag(String s) {
        StringBuilder builder = new StringBuilder();
        switch (s.charAt(3)) {
            case '0':
                break;
            case '1':
                builder.append("ACC_PUBLIC ");
                break;
            case '2':
                builder.append("ACC_PRIVATE  ");
                break;
            case '4':
                builder.append("ACC_PROTECTED  ");
                break;
            case '8':
                builder.append("ACC_STATIC  ");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        switch (s.charAt(2)) {
            case '0':
                break;
            case '1':
                builder.append("ACC_FINAL ");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        switch (s.charAt(1)) {
            case '0':
                break;
            case '2':
                builder.append("ACC_INTERFACE  ");
                break;
            case '4':
                builder.append("ACC_ABSTRACT  ");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        switch (s.charAt(0)) {
            case '0':
                break;
            case '1':
                builder.append("ACC_SYNTHETIC ");
                break;
            case '2':
                builder.append("ACC_ANNOTATION  ");
                break;
            case '4':
                builder.append("ACC_ENUM  ");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        return builder.toString();
    }

    @Getter
    @Setter
    class Clazz {

        private ConstantClassInfo inner_class_info;

        private ConstantClassInfo outer_class_info;

        private ConstantUtf8Info inner_name;

        private String inner_class_access_flag;

    }
}
