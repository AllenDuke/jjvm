package com.github.allenduke.jjvm.instructions.constants.operands;

import com.github.allenduke.jjvm.instructions.base.BytecodeReader;
import com.github.allenduke.jjvm.instructions.base.Instruction;
import com.github.allenduke.jjvm.rtda.Frame;

/* 从操作数中读取一个byte，扩展成int，压入操作数栈顶 */
public class bipush implements Instruction {

    private int val;

    @Override
    public int getOpCode() {
        return CODE_bipush;
    }

    @Override
    public String getReName() {
        return "bipush";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushInt(val);
    }
}
