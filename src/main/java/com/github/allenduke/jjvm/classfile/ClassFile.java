package com.github.allenduke.jjvm.classfile;

import com.github.allenduke.jjvm.classfile.attribute.AttributeInfo;
import com.github.allenduke.jjvm.classfile.constant.ConstantInfo;
import com.github.allenduke.jjvm.classfile.constant.ConstantUtf8Info;
import com.github.allenduke.jjvm.classfile.field.FieldInfo;
import com.github.allenduke.jjvm.classfile.method.MethodInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/* class文件大端的形式（高位在低地址） */
/*
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
*/
public class ClassFile {

    private long magic;                         /* 魔数，4字节，0xcafebabe */

    private int minorVersion;                   /* 次版本号, u2 */

    private int majorVersion;                   /* 主版本号, u2 */

    private int constantPoolCount;              /* 常量数量 */

    private ConstantInfo[] constantPool;        /* 常量池 */

    private String accessFlags;                 /* 类访问标志，类 接口 public private, 16bit */

    private String className;                   /* 类名 */

    private String superClassName;              /* 父类名 */

    private int interfacesCount;                /* 接口数量 */

    private ConstantUtf8Info[] interfaces;      /* 接口表 */

    private int fieldsCount;                    /* 字段数量 */

    private FieldInfo[] fields;                 /* 字段表 */

    private int methodsCount;                   /* 方法数量 */

    private MethodInfo[] methods;               /* 方法表 */

    private int attributesCount;                /* 属性数量 */

    private AttributeInfo[] attributes;         /* 属性表 */

    public MethodInfo getMainMethod() {
        for (MethodInfo m : methods) {
            if ("main".equals(m.getName()) && "([Ljava/lang/String;)V".equals(m.getDescriptor())) {
                return m;
            }
        }
        return null;
    }

    public String[] getInterfaceNames() {
        String[] names = new String[interfacesCount];
        for (int i = 0; i < interfacesCount; i++) {
            names[i] = interfaces[i].parseString();
        }
        return names;
    }

}
