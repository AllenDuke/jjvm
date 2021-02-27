package com.github.allenduke.jjvm.classfile.attribute;

import com.github.allenduke.jjvm.classfile.ClassFile;
import com.github.allenduke.jjvm.classfile.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeAttribute extends AttributeInfo {
    //U2
    private int maxStack;
    //U2
    private int maxLocals;
    //U4
    private int codeLength;
    //U1[]
    private byte[] code;
    //U2
    private int exception_table_length;

    private Exception_table[] exception_table;
    //U2
    private int attributes_count;

    private AttributeInfo[] attributes;

    @Override
    public String getName() {
        return "Code";
    }

    @Override
    public CodeAttribute parseAttribute(ClassFile classFile) {
        setIndex(0);
        if (!getName().equals(this.getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        maxStack = read(2);
        maxLocals = read(2);
        codeLength = read(4);
        code = readBytes(codeLength);
        exception_table_length = read(2);

        Exception_table[] exception_tables = new Exception_table[exception_table_length];
        for (int i = 0; i < exception_table_length; i++) {
            Exception_table exception_table = new Exception_table();
            exception_table.setStart_pc(read(2));
            exception_table.setEnd_pc(read(2));
            exception_table.setHandler_pc(read(2));
            exception_table.setCatch_type(read(2));
            exception_tables[i] = exception_table;
        }
        this.setException_table(exception_tables);
        attributes_count = read(2);
        AttributeInfo[] attribute_infos = new AttributeInfo[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            int pool_index = read(2);
            ConstantUtf8Info attributeName = (ConstantUtf8Info) classFile.getConstantPool()[pool_index];
            AttributeInfo attribute_info = AttributeInfo.getInstance(attributeName.parseString());
            attribute_info.setAttributeName(attributeName.parseString());
            int u4 = read(4);
            attribute_info.setAttributeLength(u4);
            attribute_info.setInfo(readBytes(u4));
            attribute_infos[i] = attribute_info.parseAttribute(classFile);
        }
        setAttributes(attribute_infos);
        CodeAttribute codeAttribute = new CodeAttribute();
        codeAttribute.maxLocals = maxLocals;
        codeAttribute.maxStack = maxStack;
        codeAttribute.attributes = attributes;
        codeAttribute.attributes_count = attributes_count;
        codeAttribute.code = code;
        codeAttribute.codeLength = codeLength;
        codeAttribute.exception_table = exception_table;
        codeAttribute.exception_table_length = exception_table_length;
        return codeAttribute;
    }


    @Getter
    @Setter
    class Exception_table {
        private int start_pc;

        private int end_pc;

        private int handler_pc;

        private int catch_type;
    }
}
