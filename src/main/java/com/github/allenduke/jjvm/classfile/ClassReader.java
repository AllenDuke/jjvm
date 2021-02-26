package com.github.allenduke.jjvm.classfile;

import com.github.allenduke.jjvm.classfile.attribute.AttributeInfo;
import com.github.allenduke.jjvm.classfile.constant.ConstantInfo;
import com.github.allenduke.jjvm.classfile.constant.ConstantClassInfo;
import com.github.allenduke.jjvm.classfile.constant.ConstantUtf8Info;
import com.github.allenduke.jjvm.classfile.field.FieldInfo;
import com.github.allenduke.jjvm.classfile.method.MethodInfo;

import java.util.Arrays;

/* 从一个class文件中读取数据，流式，读一个少一个 */
public class ClassReader {

    private int pos;        /* 记录当前要处理的下标 */

    private byte[] bytes;

    public ClassReader(byte[] bytes) {
        this.bytes = bytes;
        pos = 0;
    }

    public ClassFile parseClassFile() {
        ClassFile classFile = new ClassFile();
        parseMagic(classFile);
        parseMinorVersion(classFile);
        parseMajorVersion(classFile);
        parseConstantPoolCount(classFile);
        parseConstantPool(classFile);
        parseAccessFlags(classFile);
        parseThisClass(classFile);
        parseSuperClass(classFile);
        parseInterfacesCount(classFile);
        parseInterfaces(classFile);
        parseFieldsCount(classFile);
        parseFields(classFile);
        parseMethodCount(classFile);
        parseMethods(classFile);
        parseAttributesCount(classFile);
        parseAttributes(classFile);
        return classFile;
    }

    private void parseAttributes(ClassFile classFile) {
        AttributeInfo[] attribute_infos = new AttributeInfo[classFile.getAttributesCount()];
        for (int i = 0; i < attribute_infos.length; i++) {
            ConstantUtf8Info attributeName = (ConstantUtf8Info) classFile.getConstantPool()[readU2()];
            AttributeInfo attribute_info = AttributeInfo.getInstance(attributeName.parseString());
            attribute_info.setAttributeName(attributeName.parseString());
            int u4 = readU4Int();
            attribute_info.setAttributeLength(u4);
            attribute_info.setInfo(readBytes(u4));
            attribute_infos[i] = attribute_info.parseAttribute(classFile);
        }
        classFile.setAttributes(attribute_infos);
    }

    private void parseAttributesCount(ClassFile classFile) {
        classFile.setAttributesCount(readU2());
    }

    private void parseMethods(ClassFile classFile) {
        MethodInfo[] method_infos = new MethodInfo[classFile.getMethodsCount()];
        for (int i = 0; i < method_infos.length; i++) {
            MethodInfo method_info = new MethodInfo();
            String s = toHexString(readU2Byte());
            if (s.length() < 4) {
                for (; 0 < 4 - s.length(); ) {
                    s = "0" + s;
                }
            }
            method_info.setAccessFlags(s);
            ConstantUtf8Info constant_utf8_info =
                    (ConstantUtf8Info) classFile.getConstantPool()[readU2()];
            method_info.setName(constant_utf8_info.parseString());
            ConstantUtf8Info constant_utf8_info2 =
                    (ConstantUtf8Info) classFile.getConstantPool()[readU2()];
            method_info.setDescriptor(constant_utf8_info2.parseString());
            int attribute_count = readU2();
            method_info.setAttributesCount(attribute_count);

            AttributeInfo[] attribute_infos = new AttributeInfo[attribute_count];
            for (int j = 0; j < attribute_count; j++) {
                ConstantUtf8Info attributeName = (ConstantUtf8Info) classFile.getConstantPool()[readU2()];
                AttributeInfo attribute_info = AttributeInfo.getInstance(attributeName.parseString());
                attribute_info.setAttributeName(attributeName.parseString());
                int u4 = readU4Int();
                attribute_info.setAttributeLength(u4);
                attribute_info.setInfo(readBytes(u4));
                attribute_infos[j] = attribute_info.parseAttribute(classFile);
            }
            method_info.setAttributes(attribute_infos);
            method_infos[i] = method_info;
        }
        classFile.setMethods(method_infos);
    }

    private void parseMethodCount(ClassFile classFile) {
        classFile.setMethodsCount(readU2());
    }

    private void parseFields(ClassFile classFile) {
        FieldInfo[] field_infos = new FieldInfo[classFile.getFieldsCount()];
        for (int i = 0; i < field_infos.length; i++) {
            FieldInfo field_info = new FieldInfo();
            String s = toHexString(readU2Byte());
            if (s.length() < 4) {
                for (; 0 < 4 - s.length(); ) {
                    s = "0" + s;
                }
            }
            field_info.setAccessFlag(s);
            ConstantUtf8Info constant_utf8_info =
                    (ConstantUtf8Info) classFile.getConstantPool()[readU2()];
            field_info.setName(constant_utf8_info.parseString());
            ConstantUtf8Info constant_utf8_info2 =
                    (ConstantUtf8Info) classFile.getConstantPool()[readU2()];
            field_info.setDescriptor(constant_utf8_info2.parseString());
            int attribute_count = readU2();
            field_info.setAttributesCount(attribute_count);

            AttributeInfo[] attribute_infos = new AttributeInfo[attribute_count];
            for (int j = 0; j < attribute_count; j++) {
                ConstantUtf8Info attributeName = (ConstantUtf8Info) classFile.getConstantPool()[readU2()];
                AttributeInfo attribute_info = AttributeInfo.getInstance(attributeName.parseString());
                attribute_info.setAttributeName(attributeName.parseString());
                int u4 = readU4Int();
                attribute_info.setAttributeLength(u4);
                attribute_info.setInfo(readBytes(u4));
                attribute_infos[j] = attribute_info.parseAttribute(classFile);
            }
            field_info.setAttributes(attribute_infos);
            field_infos[i] = field_info;
        }
        classFile.setFields(field_infos);
    }

    private void parseFieldsCount(ClassFile classFile) {
        classFile.setFieldsCount(readU2());
    }

    private void parseInterfaces(ClassFile classFile) {
        ConstantUtf8Info[] infos = new ConstantUtf8Info[classFile.getInterfacesCount()];
        for (int i = 0; i < classFile.getInterfacesCount(); i++) {
            ConstantClassInfo constant_class_info =
                    (ConstantClassInfo) classFile.getConstantPool()[readU2()];
            ConstantUtf8Info constant_utf8_info =
                    (ConstantUtf8Info) classFile.getConstantPool()[constant_class_info.getNameIndex()];
            infos[i] = constant_utf8_info;
        }
        classFile.setInterfaces(infos);
    }

    private void parseInterfacesCount(ClassFile classFile) {
        classFile.setInterfacesCount(readU2());
    }

    private void parseThisClass(ClassFile classFile) {
        ConstantClassInfo constant_class_info =
                (ConstantClassInfo) classFile.getConstantPool()[readU2()];
        ConstantUtf8Info constant_utf8_info =
                (ConstantUtf8Info) classFile.getConstantPool()[constant_class_info.getNameIndex()];
        classFile.setClassName(constant_utf8_info.parseString());
    }

    private void parseSuperClass(ClassFile classFile) {
        int index = readU2();
        if (index == 0) {
            classFile.setSuperClassName("Object");
            return;
        }
        ConstantClassInfo constant_class_info =
                (ConstantClassInfo) classFile.getConstantPool()[index];
        ConstantUtf8Info constant_utf8_info =
                (ConstantUtf8Info) classFile.getConstantPool()[constant_class_info.getNameIndex()];
        classFile.setSuperClassName(constant_utf8_info.parseString());
    }

    private void parseAccessFlags(ClassFile classFile) {
        String s = toHexString(readU2Byte());
        if (s.length() < 4) {
            for (; 0 < 4 - s.length(); ) {
                s = "0" + s;
            }
        }
        classFile.setAccessFlags(s);
    }

    private String accessFlagsToString(String s) {
        StringBuilder builder = new StringBuilder();
        switch (s.charAt(3)) {
            case '0':
                break;
            case '1':
                builder.append("ACC_PUBLIC").append("    ");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        switch (s.charAt(2)) {
            case '0':
                break;
            case '1':
                builder.append("ACC_FINAL").append("    ");
                break;
            case '2':
                builder.append("ACC_SUPER").append("    ");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        switch (s.charAt(1)) {
            case '0':
                break;
            case '2':
                builder.append("ACC_INTERFACE").append("    ");
                break;
            case '4':
                builder.append("ACC_ABSTRACT").append("    ");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        switch (s.charAt(0)) {
            case '0':
                break;
            case '1':
                builder.append("ACC_SYNTHETIC");
                break;
            case '2':
                builder.append("ACC_ANNOTATION");
                break;
            case '4':
                builder.append("ACC_ENUM");
                break;
            default:
                throw new RuntimeException("can not parse access flag");
        }
        return builder.toString();
    }

    private void parseConstantPool(ClassFile classFile) {
        int count = classFile.getConstantPoolCount();
        ConstantInfo[] cpInfos = new ConstantInfo[count];
        cpInfos[0] = null;
        for (int i = 1; i < count; i++) {
            int tag = readU1();
            cpInfos[i] = ConstantInfo.parseConstant(tag, this);
            if (tag == ConstantInfo.TAG_LONG || tag == ConstantInfo.TAG_DOUBLE) {
                i++;
            }
        }
        classFile.setConstantPool(cpInfos);
    }

    private void parseConstantPoolCount(ClassFile classFile) {
        classFile.setConstantPoolCount(readU2());
    }

    private void parseMajorVersion(ClassFile classFile) {
        classFile.setMajorVersion(readU2());
    }

    private void parseMinorVersion(ClassFile classFile) {
        classFile.setMinorVersion(readU2());
    }

    private void parseMagic(ClassFile classFile) {
        classFile.setMagic(readU4());
    }

    public int readU1() {
        byte b = bytes[pos++];
        return 0xff & b;
    }

    public byte[] readBytes(int length) {
        byte[] bytes = Arrays.copyOfRange(this.bytes, pos, pos + length);
        pos += length;
        return bytes;
    }

    public byte readByte() {
        return bytes[pos++];
    }

    public int readU2() {
        byte bh = bytes[pos++]; /* 大端，高位在低 */
        byte bl = bytes[pos++];
        int ih = 0xff & bh;
        int il = 0xff & bl;
        return (ih << 8) | il;
    }

    public byte[] readU2Byte() {
        byte[] bytes = Arrays.copyOfRange(this.bytes, pos, pos + 2);
        pos += 2;
        return bytes;
    }

    public long readU4() {
        long lh = readU2();
        long ll = readU2();
        return (lh << 16) | ll;
    }

    public int readU4Int() {
        int ih = readU2();
        int il = readU2();
        return (ih << 16) | il;
    }

    public static String bytesToString(byte[] bytes) {
        return toHexString(bytes);
    }

    public static int bytesToInt(byte[] bytes) {
        return Integer.valueOf(toHexString(bytes), 16);
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        /**
         * 在java中，不支持无符号数。
         * 栈上的数据4字节一个单位，所以byte也是4字节的，按int来处理，高24位用符号位填充。
         */
        for (byte b : bytes) {
            builder.append(Integer.toHexString(b & 0xFF));
        }
        return builder.toString();
    }
}
