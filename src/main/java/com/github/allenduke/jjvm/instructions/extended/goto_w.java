package com.github.allenduke.jjvm.instructions.extended;

import com.github.allenduke.jjvm.instructions.base.BranchInstruction;
import com.github.allenduke.jjvm.instructions.base.BytecodeReader;
import com.github.allenduke.jjvm.rtda.Frame;

public class goto_w extends BranchInstruction {

    @Override
    public int getOpCode() {
        return CODE_goto_w;
    }

    @Override
    public void fetchOperands(BytecodeReader reader) throws Exception {
        offset = reader.readInt32();
    }

    @Override
    public void execute(Frame frame) throws Exception {
        branch(frame, offset);
    }
}
