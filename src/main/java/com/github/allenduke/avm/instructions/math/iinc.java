package com.github.allenduke.avm.instructions.math;

import com.github.allenduke.avm.instructions.base.BytecodeReader;
import com.github.allenduke.avm.instructions.base.Index8Instruction;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.LocalVars;
import lombok.Setter;

/* 为局部变量表中的int变量增加常量值 如：int i=0; i++; i+=127; i+=128 */
public class iinc extends Index8Instruction {

    @Setter
    private int constVal;

    @Override
    public int getOpCode() {
        return CODE_iinc;
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        // fixme 对于index constVal长度大于8bit的情况，iinc指令前会有wide指令，那么接下来就不是read8了
        this.index = reader.readUInt8();
        this.constVal = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) throws Exception {
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(index);
        val += constVal;
        localVars.setInt(index, val);
    }
}
