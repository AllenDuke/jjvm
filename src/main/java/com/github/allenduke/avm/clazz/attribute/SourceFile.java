package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import com.github.allenduke.avm.clazz.ClassReader;
import com.github.allenduke.avm.clazz.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceFile extends AttributeInfo {

  private String source_file;

  @Override
  public String getName() {
    return "SourceFile";
  }

  @Override
  public SourceFile parseAttribute(ClassFile classFile) {
    if (!getName().equals(this.getAttribute_name())) {
      throw new RuntimeException("parse source file exception");
    }
    if (2 != this.getAttribute_length()) {
      throw new RuntimeException("parse source file exception");
    }
    if (2 != this.getInfo().length) {
      throw new RuntimeException("parse source file exception");
    }
    int index = ClassReader.bytesToInt(this.getInfo());
    ConstantUtf8Info constant_utf8_info = (ConstantUtf8Info) classFile.getConstantPool()[index];
    this.setSource_file(constant_utf8_info.parseString());
    return this;
  }
}
