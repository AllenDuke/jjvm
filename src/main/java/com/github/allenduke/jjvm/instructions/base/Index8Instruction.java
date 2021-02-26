package com.github.allenduke.jjvm.instructions.base;

import com.github.allenduke.jjvm.rtda.Frame;
import lombok.Setter;

public abstract class Index8Instruction implements Instruction {

    @Setter
    protected int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUInt8();
    }

    @Override
    public abstract void execute(Frame frame) throws Exception;
}
