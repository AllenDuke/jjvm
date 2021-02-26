package com.github.allenduke.jjvm.instructions.base;

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

    public int readUInt8(){
        int b = code[pc++];
        return b & 0xFF;
    }

    public int readInt8(){
        int b = code[pc++];
        return b;
    }

    public int readUInt16(){
        byte bh = code[pc++];   /* 大端，高位在低 */
        byte bl = code[pc++];
        int ih = 0xff & bh;
        int il = 0xff & bl;
        return (ih << 8) | il;
    }

    public int readInt16(){
        int ih = code[pc++];   /* 大端，高位在低 */
        int il = code[pc++];
        return (ih << 8) | il;
    }

    public int readUInt32(){
        int ih = readUInt16();
        int il = readUInt16();
        return (ih << 16) | il;
    }

    public int readInt32(){
        int ih = readInt16();
        int il = readInt16();
        return (ih << 16) | il;
    }

    public int[] readInt32s(int length) {
        int[] ints = new int[length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = readInt32();
        }
        return ints;
    }

    public void skipPadding() {
        while (pc % 4 != 0) {
            readUInt8();
        }
    }
}
