package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineNumberTable extends AttributeInfo {

    private int line_number_table_length;

    private Line_number_table[] line_number_table;

    @Override
    public String getName() {
        return "LineNumberTable";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        line_number_table_length = read(2);
        Line_number_table[] line_number_tables = new Line_number_table[line_number_table_length];
        for (int i = 0; i < line_number_table_length; i++) {
            Line_number_table line_number_table = new Line_number_table();
            line_number_table.setStart_pc(read(2));
            line_number_table.setLine_number(read(2));
            line_number_tables[i] = line_number_table;
        }
        this.setLine_number_table(line_number_tables);
        return this;
    }

    @Getter
    @Setter
    class Line_number_table {

        private int start_pc;

        private int line_number;

    }
}
