package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MethodParameters extends AttributeInfo {

  @Override
  public String getName() {
    return "MethodParameters";
  }

  @Override
  public MethodParameters parseAttribute(ClassFile classFile) {
    setIndex(0);
    if (!getName().equals(this.getAttribute_name())) {
      throw new RuntimeException("parse source file exception");
    }
    return this;
  }
}
