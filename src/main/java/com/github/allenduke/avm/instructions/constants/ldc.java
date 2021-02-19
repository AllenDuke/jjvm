package com.github.allenduke.avm.instructions.constants;

import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

/* ldc系列指令从常量池中加载常量，压入操作数栈 */
public class ldc extends Index8Instruction {

    @Override
    public int getOpCode() {
        return 0x12;
    }

    @Override
    public String getReName() {
        return "ldc";
    }

    @Override
    public void execute(Frame frame) {

    }
}
