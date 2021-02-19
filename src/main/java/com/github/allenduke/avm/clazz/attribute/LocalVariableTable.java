package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import com.github.allenduke.avm.clazz.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocalVariableTable extends AttributeInfo {

    private int localVariableInfosLength;

    private LocalVariableEntry[] localVariableEntries;

    @Override
    public String getName() {
        return "LocalVariableTable";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        localVariableInfosLength = read(2);
        LocalVariableEntry[] entries = new LocalVariableEntry[localVariableInfosLength];
        for (int i = 0; i < localVariableInfosLength; i++) {
            LocalVariableEntry entry = new LocalVariableEntry();
            entry.setStartPc(read(2));
            entry.setLength(read(2));
            ConstantUtf8Info constant = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
            entry.setName(constant.parseString());
            ConstantUtf8Info constant2 = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
            entry.setDescriptor(constant2.parseString());
            entry.setIndex(read(2));
            entries[i] = entry;
        }
        setLocalVariableEntries(entries);
        return this;
    }

    @Setter
    @Getter
    class LocalVariableEntry {
        private int startPc;

        private int length;

        private String name;

        private String descriptor;

        private int index;
    }
}
