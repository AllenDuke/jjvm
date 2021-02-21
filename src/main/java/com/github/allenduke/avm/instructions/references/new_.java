package com.github.allenduke.avm.instructions.references;

import com.github.allenduke.avm.instructions.base.Index16Instruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.heap.Class;
import com.github.allenduke.avm.rtda.heap.ClassRef;
import com.github.allenduke.avm.rtda.heap.ConstantPool;

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


    }
}
