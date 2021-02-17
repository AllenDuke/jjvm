package com.github.allenduke.avm.instructions.base;

import com.github.allenduke.avm.rtda.Frame;


public abstract class NoOperandsInstruction implements Instruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public abstract void execute(Frame frame) throws Exception;
}
