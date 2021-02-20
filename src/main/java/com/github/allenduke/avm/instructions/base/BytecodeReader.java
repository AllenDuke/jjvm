package com.github.allenduke.avm.instructions.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BytecodeReader {

    private byte[] code;

    private int pc;         /* 记录当前要处理的下标 */

    public BytecodeReader(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public BytecodeReader reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
        return this;
    }

    public int read8() {
        int b = code[pc++];
        return b & 0xFF;
    }

    public int read16() {
        byte bh = code[pc++];   /* 大端，高位在低 */
        byte bl = code[pc++];
        int ih = 0xff & bh;
        int il = 0xff & bl;
        return (ih << 8) | il;
    }

    public int read32() {
        int ih = read16();
        int il = read16();
        return (ih << 16) | il;
    }

    public int[] readInt32s(int length) {
        int[] ints = new int[length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = read32();
        }
        return ints;
    }

    public void skipPadding() {
        while (pc % 4 != 0) {
            read8();
        }
    }
}
