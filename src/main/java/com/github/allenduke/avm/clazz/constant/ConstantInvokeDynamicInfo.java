package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantInvokeDynamicInfo extends ConstantInfo {


    private int bootstrap_method_attr_index;

    private int name_and_type_index;


    @Override
    public int getTag() {
        return 18;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantInvokeDynamicInfo constant = new ConstantInvokeDynamicInfo();
        constant.setTag(getTag());
        constant.setBootstrap_method_attr_index(classReader.readU2());
        constant.setName_and_type_index(classReader.readU2());
        return constant;
    }
}
