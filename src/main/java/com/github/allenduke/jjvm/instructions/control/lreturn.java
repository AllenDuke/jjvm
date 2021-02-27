package com.github.allenduke.jjvm.instructions.control;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.JThread;

public class lreturn extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_lreturn;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        JThread jthread = frame.getJthread();
        Frame curFrame = jthread.popFrame();
        Frame invokerFrame = jthread.topFrame();
        long retVal = curFrame.getOperandStack().popLong();
        invokerFrame.getOperandStack().pushLong(retVal);
    }
}
