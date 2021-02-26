package com.github.allenduke.jjvm.instructions.base;

import com.github.allenduke.jjvm.rtda.Frame;


public abstract class Index16Instruction implements Instruction {

    protected int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUInt16();
    }

    @Override
    public abstract void execute(Frame frame);
}
