package com.github.allenduke.jjvm.instructions.control;

import com.github.allenduke.jjvm.instructions.base.NoOperandsInstruction;
import com.github.allenduke.jjvm.rtda.Frame;
import com.github.allenduke.jjvm.rtda.JThread;
import com.github.allenduke.jjvm.rtda.heap.AObject;

public class areturn extends NoOperandsInstruction {
    @Override
    public int getOpCode() {
        return CODE_areturn;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        JThread jthread = frame.getJthread();
        Frame curFrame = jthread.popFrame();
        Frame invokerFrame = jthread.topFrame();
        AObject retVal = curFrame.getOperandStack().popRef();
        invokerFrame.getOperandStack().pushRef(retVal);
    }
}
