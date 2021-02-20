package com.github.allenduke.avm.instructions.control;

import com.github.allenduke.avm.instructions.base.BranchInstruction;
import com.github.allenduke.avm.instructions.base.BytecodeReader;
import com.github.allenduke.avm.rtda.Frame;

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
