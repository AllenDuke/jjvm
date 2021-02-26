package com.github.allenduke.jjvm.instructions.constants.nooperands;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

/* 什么都不用做 */
public class nop extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_nop;
    }

    @Override
    public String getReName() {
        return "nop";
    }

    @Override
    public void execute(Frame frame) {

    }
}
