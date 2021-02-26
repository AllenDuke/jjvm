package com.github.allenduke.jjvm.rtda.heap;

import com.github.allenduke.jjvm.classfile.constant.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * @author allen
 * @description 运行时常量池主要存放两类信息：字面量和符号引用。
 * 字面量：整数，浮点数，字符串字面量。
 * 符号引用：类符号引用，字段符号引用，方法符号引用，接口方法符号引用。
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public class ConstantPool {

    private Class clazz;

    private Object[] constants;

    public static ConstantPool newConstantPool(Class clazz, ConstantInfo[] constantInfos) {
        Object[] constants = new Object[constantInfos.length];
        ConstantPool constantPool = new ConstantPool();
        constantPool.clazz = clazz;
        constantPool.constants = constants;
        for (int i = 1; i < constants.length; i++) {
            ConstantInfo info = constantInfos[i];
            String simpleName = info.getClass().getSimpleName();
            switch (simpleName) {
                case "ConstantIntegerInfo":
                    ConstantIntegerInfo integerInfo = (ConstantIntegerInfo) info;
                    constants[i] = integerInfo.getValue();
                    break;
                case "ConstantFloatInfo":
                    ConstantFloatInfo floatInfo = (ConstantFloatInfo) info;
                    constants[i] = floatInfo.getValue();
                    break;
                case "ConstantLongInfo":
                    ConstantLongInfo longInfo = (ConstantLongInfo) info;
                    constants[i] = longInfo.getValue();
                    i++;
                    break;
                case "ConstantDoubleInfo":
                    ConstantDoubleInfo doubleInfo = (ConstantDoubleInfo) info;
                    constants[i] = doubleInfo.getValue();
                    i++;
                    break;
                case "ConstantStringInfo":
                    ConstantStringInfo stringInfo = (ConstantStringInfo) info;
                    ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantInfos[stringInfo.getStringIndex()];
                    constants[i] = utf8Info.parseString();
                    break;
                case "ConstantClassInfo":
                    ConstantClassInfo classInfo = (ConstantClassInfo) info;
                    constants[i] = ClassRef.newClassRef(constantPool, constantInfos, classInfo);
                    break;
                case "ConstantFieldRefInfo":
                    ConstantFieldRefInfo fieldrefInfo = (ConstantFieldRefInfo) info;
                    constants[i] = FieldRef.newFieldRef(constantPool, constantInfos, fieldrefInfo);
                    break;
                case "ConstantMethodRefInfo":
                    ConstantMethodRefInfo methodRefInfo = (ConstantMethodRefInfo) info;
                    constants[i] = MethodRef.newMethodRef(constantPool, constantInfos, methodRefInfo);
                    break;
                case "ConstantInterfaceMethodRefInfo":
                    ConstantInterfaceMethodRefInfo interfaceMethodRefInfo = (ConstantInterfaceMethodRefInfo) info;
                    constants[i] = InterfaceMethodRef.newInterfaceMethodRef(constantPool, constantInfos,
                            interfaceMethodRefInfo);
                    break;
                default:
                    // todo
            }
        }
        return constantPool;
    }

    // todo index为uint
    public Object getConstant(int index){
        Object constant = constants[index];
        if(constant ==null) throw new RuntimeException("No constants at index "+index);
        return constant;
    }

}
