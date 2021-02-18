package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstantInvokeDynamicInfo extends ConstantInfo {


    private int bootstrapMethodAttrIndex;

    private int nameAndTypeIndex;


    @Override
    public int getTag() {
        return TAG_INVOKE_DYNAMIC;
    }

    @Override
    public ConstantInfo parse(ClassReader classReader) {
        ConstantInvokeDynamicInfo constant = new ConstantInvokeDynamicInfo();
        constant.setTag(getTag());
        constant.setBootstrapMethodAttrIndex(classReader.readU2());
        constant.setNameAndTypeIndex(classReader.readU2());
        return constant;
    }
}
