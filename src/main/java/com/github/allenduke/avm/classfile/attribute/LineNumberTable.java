package com.github.allenduke.avm.classfile.attribute;

import com.github.allenduke.avm.classfile.ClassFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineNumberTable extends AttributeInfo {

    private int lineNumberEntriesLength;

    private LineNumberEntry[] lineNumberEntries;

    @Override
    public String getName() {
        return "LineNumberTable";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        lineNumberEntriesLength = read(2);
        LineNumberEntry[] lineNumberInfos = new LineNumberEntry[lineNumberEntriesLength];
        for (int i = 0; i < lineNumberEntriesLength; i++) {
            LineNumberEntry info = new LineNumberEntry();
            info.setStartPc(read(2));
            info.setLineNumber(read(2));
            lineNumberInfos[i] = info;
        }
        this.setLineNumberEntries(lineNumberInfos);
        return this;
    }

    @Getter
    @Setter
    class LineNumberEntry {

        private int startPc;

        private int lineNumber;

    }
}
