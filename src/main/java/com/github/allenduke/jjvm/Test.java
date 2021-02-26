package com.github.allenduke.jjvm;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/10
 */
public class Test {

    public static int staticVar;

    public int instanceVar;

    public static void main(String[] args) {
        int x = 32768;
        Test test = new Test();
        Test.staticVar = x;
        x = Test.staticVar;
        test.instanceVar = x;
        x = test.instanceVar;
        Object o = test;
        if (o instanceof Test) {
            test = (Test) o;
            System.out.println(test.instanceVar);
        }
    }
}
