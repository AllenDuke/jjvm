package com.github.allenduke.jjvm.rtda;

import com.github.allenduke.jjvm.rtda.heap.Method;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Frame {

    private Slots slots;        /* 局部变量表 */

    private OperandStack operandStack;  /* 操作数栈 */

    private JThread jthread;

    private int nextPc;                 /* 下一条字节码索引 */

    private Method method;              /* 当前栈帧所属方法 */

    public Frame(JThread jthread, int maxLocals, int maxStack,Method method) {
        this.jthread = jthread;
        this.slots = new Slots(maxLocals);
        this.operandStack = new OperandStack(maxStack);
        this.method = method;
    }

}
