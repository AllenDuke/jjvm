package com.github.allenduke.avm.instructions.constants;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class iconst_2 extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return 0x05;
    }

    @Override
    public String getReName() {
        return "iconst_2";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushInt(2);
    }
}
