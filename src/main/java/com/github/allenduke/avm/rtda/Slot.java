package com.github.allenduke.avm.rtda;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/* 一个slot应该容纳一个int或者一个指针 */
public class Slot {

    private int num;        /* double long 大端存储，高位在低 */

    private Object ref;     /* 这是一个引用 */

}
