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
/**
 * 用于实现switch语句，形式：
 * switch(i){
 *     case 0:;
 *     case 1:;
 *     case 2:;
 *     default:;
 * }
 */
public class tableswitch extends BranchInstruction {

    private int defaultOffset;      /* 默认情况下，要跳转的字节码的偏移量 */

    private int low;

    private int high;

    private int[] jumpOffsets;

    @Override
    public int getOpCode() {
        return CODE_tableswitch;
    }


    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();       /* 会有0~3字节的填充，defaultOffset在字节码中对齐4字节 */
        defaultOffset = reader.readInt32();
        low = reader.readInt32();
        high = reader.readInt32();
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
        branch(frame,offset);
    }
}
