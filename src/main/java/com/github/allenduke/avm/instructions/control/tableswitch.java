package com.github.allenduke.avm.instructions.control;

import com.github.allenduke.avm.instructions.base.BranchInstruction;
import com.github.allenduke.avm.instructions.base.BytecodeReader;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;
import com.github.allenduke.avm.rtda.JThread;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class tableswitch extends BranchInstruction {

    private int defaultOffset;

    private int low;

    private int high;

    private int[] jumpOffsets;

    @Override
    public int getOpCode() {
        return 0xaa;
    }


    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        low = reader.read32();
        high = reader.read32();
        jumpOffsets = reader.readInt32s(high - low + 1);
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        int index = operandStack.popInt();
        if (index >= low && index <= high) {
            offset = jumpOffsets[index - low];
        } else {
            offset = defaultOffset;
        }
        JThread jthread = frame.getJthread();
        jthread.setPc(offset);
    }
}
