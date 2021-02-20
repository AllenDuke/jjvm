package com.github.allenduke.avm.rtda;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/* 一个slot应该容纳一个int或者一个指针，在c/c++中，可以使用一个u64，或者利用联合体 */
public class Slot {

    private int num;        /* double long 大端存储，高位在低 */

    private Object ref;     /* 这是一个引用 */

}
