package com.github.allenduke.avm.instructions.extended;

import com.github.allenduke.avm.instructions.base.BranchInstruction;
import com.github.allenduke.avm.instructions.base.BytecodeReader;
import com.github.allenduke.avm.rtda.Frame;

public class gotow extends BranchInstruction {

    @Override
    public int getOpCode() {
        return 0xc8;
    }

    @Override
    public void fetchOperands(BytecodeReader reader) throws Exception {
        offset = reader.read32();
    }

    @Override
    public void execute(Frame frame) throws Exception {
        branch(frame, offset);
    }
}
