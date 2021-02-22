package com.github.allenduke.avm.rtda;

import com.github.allenduke.avm.rtda.heap.AObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/* 一个slot应该容纳一个int或者一个指针，在c/c++中，可以使用一个u64，或者利用联合体 */
public class Slot {

    private int num;        /* double long 大端存储，高位在低 */

    private AObject ref;     /* 这是一个引用 */

}
