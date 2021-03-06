package com.github.allenduke.jjvm.instructions.constants.operands;

import com.github.allenduke.jjvm.instructions.base.BytecodeReader;
import com.github.allenduke.jjvm.instructions.base.Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

/* 从操作数中读取一个short，扩展成int，压入操作数栈顶 */
public class sipush implements Instruction {

    private int val;

    @Override
    public int getOpCode() {
        return CODE_sipush;
    }

    @Override
    public String getReName() {
        return "sipush";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.readInt16();
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushInt(val);
    }
}
