package com.github.allenduke.jjvm.instructions.references;

import com.github.allenduke.jjvm.instructions.base.Index16Instruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.heap.AObject;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.ClassRef;
import com.github.allenduke.jjvm.rtda.heap.ConstantPool;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/21
 */
public class new_ extends Index16Instruction {

    @Override
    public int getOpCode() {
        return CODE_new;
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(index);
        Class clazz = classRef.resolvedClass();

        if(clazz.isInterface()||clazz.isAbstract()){
            throw new RuntimeException("java.lang.InstantiationError");
        }

        AObject ref = AObject.newObject(clazz);
        frame.getOperandStack().pushRef(ref);
    }
}
