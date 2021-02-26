package com.github.allenduke.jjvm.classfile.attribute;

import com.github.allenduke.jjvm.classfile.ClassFile;
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
    if (!getName().equals(this.getAttributeName())) {
      throw new RuntimeException("parse source file exception");
    }
    return this;
  }
}
