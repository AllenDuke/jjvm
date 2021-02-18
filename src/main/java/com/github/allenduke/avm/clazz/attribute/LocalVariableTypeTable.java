package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import com.github.allenduke.avm.clazz.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocalVariableTypeTable extends AttributeInfo {

    private int local_variable_type_table_length;

    private Local_variable_table[] local_variable_type_tables;

    @Override
    public String getName() {
        return "LocalVariableTable";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        setLocal_variable_type_table_length(read(2));
        Local_variable_table[] local_variable_tables = new Local_variable_table[getLocal_variable_type_table_length()];
        for (int i = 0; i < getLocal_variable_type_table_length(); i++) {
            Local_variable_table local_variable_table = new Local_variable_table();
            local_variable_table.setStart_pc(read(2));
            local_variable_table.setLength(read(2));
            ConstantUtf8Info constant = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
            local_variable_table.setName(constant.parseString());
            ConstantUtf8Info constant2 = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
            local_variable_table.setSignature(constant2.parseString());
            local_variable_table.setIndex(read(2));
            local_variable_tables[i] = local_variable_table;
        }
        setLocal_variable_type_tables(local_variable_tables);
        return this;
    }

    @Setter
    @Getter
    class Local_variable_table {
        private int start_pc;

        private int length;

        private String name;

        private String signature;

        private int index;
    }
}
