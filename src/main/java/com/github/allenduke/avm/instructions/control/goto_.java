package com.github.allenduke.avm.instructions.control;

import com.github.allenduke.avm.instructions.base.BranchInstruction;
import com.github.allenduke.avm.instructions.base.BytecodeReader;
import com.github.allenduke.avm.rtda.Frame;

public class goto_ extends BranchInstruction {
    @Override
    public int getOpCode() {
        return 0xa7;
    }

    @Override
    public void fetchOperands(BytecodeReader reader) throws Exception {
        this.offset = reader.read16();
    }

    @Override
    public void execute(Frame frame) throws Exception {
        branch(frame, offset);
    }
}
