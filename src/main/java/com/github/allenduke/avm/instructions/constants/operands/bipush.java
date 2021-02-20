package com.github.allenduke.avm.instructions.constants.operands;

import com.github.allenduke.avm.instructions.base.BytecodeReader;
import com.github.allenduke.avm.instructions.base.Instruction;
import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

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
        val = reader.read8();
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushInt(val);
    }
}
