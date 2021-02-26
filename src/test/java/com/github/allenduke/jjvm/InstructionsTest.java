package com.github.allenduke.jjvm;

import com.github.allenduke.jjvm.classpath.Classpath;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.ClassLoader;
import com.github.allenduke.jjvm.rtda.heap.Method;
import org.junit.Test;

public class InstructionsTest {

    /**
     * 先执行MainTest 获取class文件
     *
     * @throws Exception
     */
    @Test
    public void t1() throws Exception {
        String path = "D:\\github\\jvmgo-book\\v1\\code\\java\\jvmgo_java\\ch05\\build\\classes\\java\\test\\com\\github\\jvmgo\\MainTest.class";
        Classpath cp = new Classpath("", path);
        ClassLoader classLoader = ClassLoader.newClassLoader(cp);
        Class mainClass = classLoader.loadClass(path);
        Method mainMethod = mainClass.getMainMethod();
        Interpreter.execute(mainMethod);
    }
}
