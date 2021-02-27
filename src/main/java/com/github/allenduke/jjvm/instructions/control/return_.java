package com.github.allenduke.jjvm.instructions.control;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class return_ extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_return;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getJthread().popFrame();
    }
}
