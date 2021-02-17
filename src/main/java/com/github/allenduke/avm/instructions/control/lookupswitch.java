package com.github.allenduke.avm.instructions.control;

import com.github.allenduke.avm.instructions.base.BranchInstruction;
import com.github.allenduke.avm.instructions.base.BytecodeReader;
import com.github.allenduke.avm.rtda.Frame;
import com.github.allenduke.avm.rtda.OperandStack;
import lombok.Getter;
import lombok.Setter;
import com.github.allenduke.avm.rtda.JThread;

@Setter
@Getter
public class lookupswitch extends BranchInstruction {

    private int defaultOffset;

    private int npairs;

    private int[] matchOffsets;

    @Override
    public int getOpCode() {
        return 0xab;
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.read32();
        npairs = reader.read32();
        matchOffsets = reader.readInt32s(npairs * 2);
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        JThread jthread = frame.getJthread();
        int key = operandStack.popInt();
        for (int i = 0; i < npairs * 2; i += 2) {
            if (matchOffsets[i] == key) {
                offset = matchOffsets[i + 1];
                jthread.setPc(offset);
                return;
            }
        }
        jthread.setPc(defaultOffset);
    }
}
