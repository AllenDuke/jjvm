package com.github.allenduke.avm.instructions.base;

import com.github.allenduke.avm.rtda.Frame;


public abstract class Index16Instruction implements Instruction {

    private int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.read16();
    }

    @Override
    public abstract void execute(Frame frame);
}
