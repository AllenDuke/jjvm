package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StackMapTable extends AttributeInfo {

    private int number;

    private StackMapFrame[] stackMapFrames;

    @Override
    public String getName() {
        return "StackMapTable";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        number = read(2);
        StackMapFrame[] frames = new StackMapFrame[getNumber()];
        for (int i = 0; i < getNumber(); i++) {

        }
        stackMapFrames = frames;
        return this;
    }

    class StackMapFrame {

    }
}
