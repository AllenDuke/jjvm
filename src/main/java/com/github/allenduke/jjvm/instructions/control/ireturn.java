package com.github.allenduke.jjvm.instructions.control;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.JThread;

public class ireturn extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_ireturn;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        JThread jthread = frame.getJthread();
        Frame curFrame = jthread.popFrame();
        Frame invokerFrame = jthread.topFrame();
        int retVal = curFrame.getOperandStack().popInt();
        invokerFrame.getOperandStack().pushInt(retVal);
    }
}
