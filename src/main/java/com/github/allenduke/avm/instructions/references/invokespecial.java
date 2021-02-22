package com.github.allenduke.avm.instructions.references;

import com.github.allenduke.avm.instructions.base.Index16Instruction;
import com.github.allenduke.avm.rtda.Frame;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/22
 */
public class invokespecial extends Index16Instruction {
    @Override
    public int getOpCode() {
        return CODE_invokespecial;
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popRef();
    }
}
