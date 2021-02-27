package com.github.allenduke.jjvm.instructions.control;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.JThread;

public class freturn extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_freturn;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        JThread jthread = frame.getJthread();
        Frame curFrame = jthread.popFrame();
        Frame invokerFrame = jthread.topFrame();
        float retVal = curFrame.getOperandStack().popFloat();
        invokerFrame.getOperandStack().pushFloat(retVal);
    }
}
