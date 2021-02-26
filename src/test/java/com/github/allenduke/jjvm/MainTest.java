package com.github.allenduke.jjvm;

public class MainTest {

    public static void main(String[] args) {
        byte[] bytes={0,-31};
        System.out.println(Integer.parseInt(toHexString(bytes), 16));
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        /**
         * 在java中，不支持无符号数。
         * 栈上的数据4字节一个单位，所以byte也是4字节的，按int来处理，高24位用符号位填充。
         */
        for (byte b : bytes) {
            builder.append(Integer.toHexString(b & 0xFF));
        }
        return builder.toString();
    }
}
