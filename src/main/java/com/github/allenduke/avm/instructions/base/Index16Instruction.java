package com.github.allenduke.avm.instructions.base;

import com.github.allenduke.avm.rtda.Frame;


public abstract class Index16Instruction implements Instruction {

    protected int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUInt16();
    }

    @Override
    public abstract void execute(Frame frame);
}
