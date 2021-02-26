package com.github.allenduke.jjvm.classfile.attribute;

import com.github.allenduke.jjvm.classfile.ClassFile;
import com.github.allenduke.jjvm.classfile.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuntimeVisibleAnnotations extends AttributeInfo {

    private int annotationsLength;

    private Annotation[] annotations;

    @Override
    public String getName() {
        return "RuntimeVisibleAnnotations";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        if (!getName().equals(getAttributeName())) {
            throw new RuntimeException("parse source file exception");
        }
        annotationsLength = read(2);
        Annotation[] annotations = new Annotation[annotationsLength];
        for (int i = 0; i < annotationsLength; i++) {
            Annotation annotation = new Annotation();
            ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
            annotation.setType(constantUtf8Info.parseString());
            annotation.setNum(read(2));
            AnnotationElement[] elements = new AnnotationElement[annotation.getNum()];
            for (int j = 0; j < elements.length; j++) {
                AnnotationElement element = new AnnotationElement();
                ConstantUtf8Info constant_utf8_info1 = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
                element.setElementName(constantUtf8Info.parseString());
                ElementValue value = new ElementValue();
                //TODO

                element.setElementValue(value);
                elements[j] = element;
            }
            annotation.setElements(elements);
            annotations[i] = annotation;
        }
        setAnnotations(annotations);
        return this;
    }


    @Getter
    @Setter
    class Annotation {
        private String type;

        private int num;

        private AnnotationElement[] elements;
    }

    @Getter
    @Setter
    class AnnotationElement {

        private String elementName;

        private ElementValue elementValue;
    }

    @Getter
    @Setter
    class ElementValue {

        private byte tag;

        private Object value;

    }

}
