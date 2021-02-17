package com.github.allenduke.avm.instructions.constants;

import com.github.allenduke.avm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.avm.rtda.Frame;

public class nop extends NoOperandsInstruction {


    @Override
    public int getOpCode() {
        return 0x00;
    }

    @Override
    public String getReName() {
        return "nop";
    }

    @Override
    public void execute(Frame frame) {

    }
}
