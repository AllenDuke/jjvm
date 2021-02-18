package com.github.allenduke.avm.clazz.constant;

import com.github.allenduke.avm.clazz.ClassReader;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public abstract class ConstantInfo {

  private int tag;

  public abstract int getTag();

  protected abstract ConstantInfo parse(ClassReader classReader);

  private static HashMap<Integer, ConstantInfo> map = new HashMap<>();

  public static ConstantInfo parseConstant(int tag, ClassReader classReader) {
    ConstantInfo constant = map.get(tag);
    return constant.parse(classReader);
  }

  static {
    ConstantClassInfo constant_class = new ConstantClassInfo();
    map.put(constant_class.getTag(), constant_class);
    ConstantDoubleInfo constant_double_info = new ConstantDoubleInfo();
    map.put(constant_double_info.getTag(), constant_double_info);
    ConstantFieldRef constantFieldRef = new ConstantFieldRef();
    map.put(constantFieldRef.getTag(), constantFieldRef);
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
