package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantClassInfo extends ConstantInfo {

    private int name_index;

    @Override
    public int getTag() {
        return 7;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantClassInfo constant_class = new ConstantClassInfo();
        constant_class.setTag(getTag());
        constant_class.setName_index(classReader.readU2());
        return constant_class;
    }
}
