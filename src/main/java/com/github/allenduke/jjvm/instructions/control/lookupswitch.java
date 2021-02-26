package com.github.allenduke.jjvm.instructions.control;

import com.github.allenduke.jjvm.instructions.base.BranchInstruction;
import com.github.allenduke.jjvm.instructions.base.BytecodeReader;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.OperandStack;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
/**
 * 用于实现switch语句，形式：
 * switch(i){
 *     case -100:;
 *     case 0:;
 *     case 100:;
 *     default:;
 * }
 */
public class lookupswitch extends BranchInstruction {

    private int defaultOffset;

    private int npairs;

    private int[] matchOffsets;

    @Override
    public int getOpCode() {
        return CODE_lookupswitch;
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        npairs = reader.readInt32();
        matchOffsets = reader.readInt32s(npairs * 2);
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        int key = operandStack.popInt();

        //todo 考虑用IntHashMap优化
        for (int i = 0; i < npairs * 2; i += 2) {
            if (matchOffsets[i] == key) {
                offset = matchOffsets[i + 1];
                branch(frame, offset);
                return;
            }
        }
        branch(frame, defaultOffset);
    }
}
