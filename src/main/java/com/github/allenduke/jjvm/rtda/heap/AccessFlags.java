package com.github.allenduke.jjvm.rtda.heap;

/**
 * @author allen
 * @description 访问标志 16bit
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public interface AccessFlags {

    int ACC_PUBLIC = 0x0001;        /* 类 字段 方法 */

    int ACC_PRIVATE = 0x0002;       /*    字段 方法 */

    int ACC_PROTECTED = 0X0004;     /*    字段 方法 */

    int ACC_STATIC = 0X0008;        /*    字段 方法 */

    int ACC_FINAL = 0X0010;         /* 类 字段 方法 */

    int ACC_SUPER = 0X0020;         /* 类          */

    int ACC_SYNCHRONIZED = 0X0020;  /*        方法 */

    int ACC_VOLATILE = 0X0040;      /*   字段      */

    int ACC_BRIDGE = 0X0040;        /*        方法 */

    int ACC_TRANSIENT = 0X0080;     /*   字段      */

    int ACC_VARARGS = 0X0080;       /*        方法 */

    int ACC_NATIVE = 0X0100;        /*        方法 */

    int ACC_INTERFACE = 0X0200;     /* 类          */

    int ACC_ABSTRACT = 0X0400;      /* 类      方法 */

    int ACC_STRICT = 0X0800;        /*         方法 */

    int ACC_SYNTHETIC = 0X1000;     /* 类 字段 方法 */

    int ACC_ANNOTATION = 0X2000;    /* 类          */

    int ACC_ENUM = 0X4000;          /* 类 字段      */

}
