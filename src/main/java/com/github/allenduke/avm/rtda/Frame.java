package com.github.allenduke.avm.rtda;

import com.github.allenduke.avm.rtda.heap.Method;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Frame {

    private LocalVars localVars;        /* 局部变量表 */

    private OperandStack operandStack;  /* 操作数栈 */

    private JThread jthread;

    private int nextPc;                 /* 下一条字节码索引 */

    private Method method;              /* 当前栈帧所属方法 */

    public Frame(JThread jthread, int maxLocals, int maxStack) {
        this.jthread = jthread;
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

}
