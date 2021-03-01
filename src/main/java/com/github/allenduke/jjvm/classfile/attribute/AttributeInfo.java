package com.github.allenduke.jjvm.classfile.attribute;

import com.github.allenduke.jjvm.classfile.ClassFile;
import com.github.allenduke.jjvm.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;

@Getter
@Setter
public abstract class AttributeInfo {

    private String attributeName;

    private int attributeLength;

    private byte[] info;

    public abstract String getName();

    public abstract AttributeInfo parseAttribute(ClassFile classFile);

    private static HashMap<String, AttributeInfo> map = new HashMap<>();

    static {
        CodeAttribute codeAttribute = new CodeAttribute();
        ConstantValueAttribute constantValueAttribute = new ConstantValueAttribute();
        Deprecated deprecated = new Deprecated();
        EnclosingMethod enclosingMethod = new EnclosingMethod();
        Exceptions exceptions = new Exceptions();
        InnerClasses innerClasses = new InnerClasses();
        LineNumberTable lineNumberTable = new LineNumberTable();
        LocalVariableTable localVariableTable = new LocalVariableTable();
        LocalVariableTypeTable localVariableTypeTable = new LocalVariableTypeTable();
        MethodParameters methodParameters = new MethodParameters();
        RuntimeVisibleAnnotations runtimeVisibleAnnotations = new RuntimeVisibleAnnotations();
        Signature signature = new Signature();
        SourceFile sourceFile = new SourceFile();
        SourceFileDebugExtension sourceFileDebugExtension = new SourceFileDebugExtension();
        StackMapTable stackMapTable = new StackMapTable();
        Synthetic synthetic = new Synthetic();

        map.put(codeAttribute.getName(), codeAttribute);
        map.put(constantValueAttribute.getName(), constantValueAttribute);
        map.put(deprecated.getName(), deprecated);
        map.put(enclosingMethod.getName(), enclosingMethod);
        map.put(exceptions.getName(), exceptions);
        map.put(innerClasses.getName(), innerClasses);
        map.put(lineNumberTable.getName(), lineNumberTable);
        map.put(localVariableTable.getName(), localVariableTable);
        map.put(localVariableTypeTable.getName(), localVariableTypeTable);
        map.put(methodParameters.getName(), methodParameters);
        map.put(runtimeVisibleAnnotations.getName(), runtimeVisibleAnnotations);
        map.put(signature.getName(), signature);
        map.put(sourceFile.getName(), sourceFile);
        map.put(sourceFileDebugExtension.getName(), sourceFileDebugExtension);
        map.put(stackMapTable.getName(), stackMapTable);
        map.put(synthetic.getName(), synthetic);

    }

    public static AttributeInfo getInstance(String attribute_name) {
        AttributeInfo attributeInfo = map.get(attribute_name);
//        attributeInfo.index=0;
        return attributeInfo;
    }


    private int index = 0;

    protected int read(int i) {
        int k = ClassReader.bytesToInt(Arrays.copyOfRange(getInfo(), index, index + i));
        index += i;
        return k;
    }

    protected byte[] readBytes(int length) {
        byte[] bytes = Arrays.copyOfRange(this.getInfo(), index, index + length);
        index += length;
        return bytes;
    }
}
