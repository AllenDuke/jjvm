package com.github.allenduke.jjvm.instructions.constants.nooperands;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;

public class aconst_null extends NoOperandsInstruction {

    @Override
    public int getOpCode() {
        return CODE_aconst_null;
    }

    @Override
    public String getReName() {
        return "aconst_null";
    }

    @Override
    public void execute(Frame frame) throws Exception {
        frame.getOperandStack().pushRef(null);  /* 空引用压入操作数栈 */
    }
}
