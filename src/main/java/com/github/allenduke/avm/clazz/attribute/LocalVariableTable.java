package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import com.github.allenduke.avm.clazz.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocalVariableTable extends AttributeInfo {

    private int localVariableInfosLength;

    private LocalVariableInfo[] localVariableInfos;

    @Override
    public String getName() {
        return "LocalVariableTable";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        setLocalVariableInfosLength(read(2));
        LocalVariableInfo[] infos = new LocalVariableInfo[localVariableInfosLength];
        for (int i = 0; i < localVariableInfosLength; i++) {
            LocalVariableInfo info = new LocalVariableInfo();
            info.setStartPc(read(2));
            info.setLength(read(2));
            ConstantUtf8Info constant = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
            info.setName(constant.parseString());
            ConstantUtf8Info constant2 = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
            info.setDescriptor(constant2.parseString());
            info.setIndex(read(2));
            infos[i] = info;
        }
        setLocalVariableInfos(infos);
        return this;
    }

    @Setter
    @Getter
    class LocalVariableInfo {
        private int startPc;

        private int length;

        private String name;

        private String descriptor;

        private int index;
    }
}
