package com.github.allenduke.jjvm;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/10
 */
public class Test {

    public static void main(String[] args) {
        long x = fibonacci(30);
        System.out.println(x);
    }

    private static long fibonacci(long n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
