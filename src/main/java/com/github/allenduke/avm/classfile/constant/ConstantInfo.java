package com.github.allenduke.avm.classfile.constant;

import com.github.allenduke.avm.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public abstract class ConstantInfo {

    private int tag;    /* 在java虚拟机规范中，定义了14种常量， u1 */

    public static final int TAG_UTF8 = 1;     /* interface中的变量默认 public static final */
    public static final int TAG_INTEGER = 3;
    public static final int TAG_FLOAT = 4;
    public static final int TAG_LONG = 5;
    public static final int TAG_DOUBLE = 6;
    public static final int TAG_CLASS = 7;
    public static final int TAG_STRING = 8;
    public static final int TAG_FIELD_REF = 9;
    public static final int TAG_METHOD_REF = 10;
    public static final int TAG_INTERFACE_METHOD_REF = 11;
    public static final int TAG_NAME_AND_TYPE = 12;
    public static final int TAG_METHOD_HANDLE = 15;
    public static final int TAG_METHOD_TYPE = 16;
    public static final int TAG_INVOKE_DYNAMIC = 18;

    public abstract int getTag();

    protected abstract ConstantInfo parse(ClassReader classReader);

    private static HashMap<Integer, ConstantInfo> map = new HashMap<>();

    public static ConstantInfo parseConstant(int tag, ClassReader classReader) {
        ConstantInfo constant = map.get(tag);
        return constant.parse(classReader);     /* 在parse中会重新new的 */
    }

    static {
        ConstantClassInfo constant_class = new ConstantClassInfo();
        map.put(constant_class.getTag(), constant_class);
        ConstantDoubleInfo constant_double_info = new ConstantDoubleInfo();
        map.put(constant_double_info.getTag(), constant_double_info);
        ConstantFieldRefInfo constantFieldRefInfo = new ConstantFieldRefInfo();
        map.put(constantFieldRefInfo.getTag(), constantFieldRefInfo);
        ConstantFloatInfo constant_float_info = new ConstantFloatInfo();
        map.put(constant_float_info.getTag(), constant_float_info);
        ConstantIntegerInfo constantIntegerInfo = new ConstantIntegerInfo();
        map.put(constantIntegerInfo.getTag(), constantIntegerInfo);
        ConstantInterfaceMethodRefInfo constantInterfaceMethodRefInfo =
                new ConstantInterfaceMethodRefInfo();
        map.put(constantInterfaceMethodRefInfo.getTag(), constantInterfaceMethodRefInfo);
        ConstantInvokeDynamicInfo constant_invokeDynamic_info = new ConstantInvokeDynamicInfo();
        map.put(constant_invokeDynamic_info.getTag(), constant_invokeDynamic_info);
        ConstantLongInfo constant_long_info = new ConstantLongInfo();
        map.put(constant_long_info.getTag(), constant_long_info);
        ConstantMethodHandleInfo constant_methodHandle_info = new ConstantMethodHandleInfo();
        map.put(constant_methodHandle_info.getTag(), constant_methodHandle_info);
        ConstantMethodRefInfo constantMethodRefInfo = new ConstantMethodRefInfo();
        map.put(constantMethodRefInfo.getTag(), constantMethodRefInfo);
        ConstantMethodTypeInfo constant_methodType_info = new ConstantMethodTypeInfo();
        map.put(constant_methodType_info.getTag(), constant_methodType_info);
        ConstantNameAndTypeInfo constant_nameAndType_info = new ConstantNameAndTypeInfo();
        map.put(constant_nameAndType_info.getTag(), constant_nameAndType_info);
        ConstantStringInfo constant_string_info = new ConstantStringInfo();
        map.put(constant_string_info.getTag(), constant_string_info);
        ConstantUtf8Info constant_utf8_info = new ConstantUtf8Info();
        map.put(constant_utf8_info.getTag(), constant_utf8_info);
    }
}
