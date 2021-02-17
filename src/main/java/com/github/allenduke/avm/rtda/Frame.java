package com.github.allenduke.avm.rtda;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Frame {

    private Frame lower;

    private LocalVars localVars;

    private OperandStack operandStack;

    private JThread jthread;

    private int nextPc;

    public Frame(JThread jthread, int maxLocals, int maxStack) {
        this.jthread = jthread;
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

}
