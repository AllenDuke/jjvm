package com.github.allenduke.jjvm.rtda;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JThread {

    private int pc;

    private Stack stack;

    public JThread(int maxStack) {
        this.stack = new Stack(maxStack);
    }

    public void pushFrame(Frame frame) {
        this.stack.push(frame);
    }

    public Frame popFrame() {
        return this.stack.pop();
    }

    public Frame topFrame() {
        return this.stack.top();
    }


    public boolean isStackEmpty() {
        return stack.getStacks().isEmpty();
    }
}
