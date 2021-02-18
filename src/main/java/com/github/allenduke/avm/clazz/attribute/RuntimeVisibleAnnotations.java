package com.github.allenduke.avm.clazz.attribute;

import com.github.allenduke.avm.clazz.ClassFile;
import com.github.allenduke.avm.clazz.constant.ConstantUtf8Info;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuntimeVisibleAnnotations extends AttributeInfo {

    private int num_annotations;

    private Annotation[] annotations;

    @Override
    public String getName() {
        return "RuntimeVisibleAnnotations";
    }

    @Override
    public AttributeInfo parseAttribute(ClassFile classFile) {
        setIndex(0);
        if (!getName().equals(getAttribute_name())) {
            throw new RuntimeException("parse source file exception");
        }
        setNum_annotations(read(2));
        Annotation[] annotations = new Annotation[getNum_annotations()];
        for (int i = 0; i < getNum_annotations(); i++) {
            Annotation annotation = new Annotation();
            ConstantUtf8Info constant_utf8_info = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
            annotation.setType(constant_utf8_info.parseString());
            annotation.setNum(read(2));
            Annotation_element[] annotation_elements = new Annotation_element[annotation.getNum()];
            for (int j = 0; j < annotation_elements.length; j++) {
                Annotation_element annotation_element = new Annotation_element();
                ConstantUtf8Info constant_utf8_info1 = (ConstantUtf8Info) classFile.getConstantPool()[read(2)];
                annotation_element.setElement_name(constant_utf8_info.parseString());
                Element_value element_value = new Element_value();
                //TODO

                annotation_element.setElement_value(element_value);
                annotation_elements[j] = annotation_element;
            }
            annotation.setElements(annotation_elements);
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

        private Annotation_element[] elements;
    }

    @Getter
    @Setter
    class Annotation_element {

        private String element_name;

        private Element_value element_value;
    }

    @Getter
    @Setter
    class Element_value {

        private byte tag;

        private Object value;

    }

}
