package com.github.allenduke.avm.classfile.attribute;

import com.github.allenduke.avm.classfile.ClassFile;
import com.github.allenduke.avm.classfile.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocalVariableTypeTable extends AttributeInfo {

    private int localVariableTypeEntriesLength;

    private LocalVariableTypeEntry[] localVariableTypeEntries;

    @Override
    public String getName() {
        return "LocalVariableTable";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        localVariableTypeEntriesLength = read(2);
        LocalVariableTypeEntry[] entries = new LocalVariableTypeEntry[localVariableTypeEntriesLength];
        for (int i = 0; i < localVariableTypeEntriesLength; i++) {
            LocalVariableTypeEntry entry = new LocalVariableTypeEntry();
            entry.setStart_pc(read(2));
            entry.setLength(read(2));
            ConstantUtf8Info constant = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
            entry.setName(constant.parseString());
            ConstantUtf8Info constant2 = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
            entry.setSignature(constant2.parseString());
            entry.setIndex(read(2));
            entries[i] = entry;
        }
        setLocalVariableTypeEntries(entries);
        return this;
    }

    @Setter
    @Getter
    class LocalVariableTypeEntry {
        private int start_pc;

        private int length;

        private String name;

        private String signature;

        private int index;
    }
}
