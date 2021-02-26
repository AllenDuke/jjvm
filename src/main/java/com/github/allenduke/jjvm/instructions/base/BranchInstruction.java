package com.github.allenduke.jjvm.instructions.base;

import com.github.allenduke.jjvm.rtda.Frame;

/* 跳转指令 */
public abstract class BranchInstruction implements Instruction {

    protected int offset;       /* 跳转的偏移量 */

    @Override
    public void fetchOperands(BytecodeReader reader) throws Exception {
        this.offset = reader.readInt16();
    }

    @Override
    public abstract void execute(Frame frame) throws Exception;

    public void branch(Frame frame, int offset) {
        int pc = frame.getJthread().getPc();
        frame.setNextPc(pc + offset);
    }
}
