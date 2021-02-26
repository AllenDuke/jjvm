package com.github.allenduke.jjvm.instructions.control;

import com.github.allenduke.jjvm.instructions.base.BranchInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class goto_ extends BranchInstruction {
    @Override
    public int getOpCode() {
        return CODE_goto;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        branch(frame, offset);
    }
}
