package com.github.allenduke.avm.instructions.math;

import com.github.allenduke.avm.instructions.base.BytecodeReader;
import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.LocalVars;
import lombok.Setter;

public class iinc extends Index8Instruction {

    @Setter
    private int constVal;

    @Override
    public int getOpCode() {
        return 0x84;
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.read8();
        this.constVal = reader.read8();
    }

    @Override
    public void execute(Frame frame) throws Exception {
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(index);
        val += constVal;
        localVars.setInt(index, val);
    }
}
