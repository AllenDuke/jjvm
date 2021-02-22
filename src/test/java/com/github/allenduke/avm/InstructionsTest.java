package com.github.allenduke.avm;

import com.github.allenduke.avm.classfile.ClassFile;
import com.github.allenduke.avm.classfile.ClassReader;
import com.github.allenduke.avm.classfile.method.MethodInfo;
import com.github.allenduke.avm.classpath.Classpath;
import com.github.allenduke.avm.rtda.heap.Class;
import com.github.allenduke.avm.rtda.heap.ClassLoader;
import com.github.allenduke.avm.rtda.heap.Method;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

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
