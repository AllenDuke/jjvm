package com.github.allenduke.jjvm;

import com.github.allenduke.jjvm.classpath.Classpath;
import com.github.allenduke.jjvm.rtda.heap.Class;
import com.github.allenduke.jjvm.rtda.heap.ClassLoader;
import com.github.allenduke.jjvm.rtda.heap.Method;

import java.util.Arrays;

/**
 * @author allen
 * @description 测试是，idea的vm参数是 -Xjre /home/allen/jdk/jdk1.8/jre java.lang.Object
 * @contact AllenDuke@163.com
 * @date 2021/2/10
 */
public class Main {

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        Args argv = Args.parse(args);
        if(!argv.ok){
            System.out.println("args parse err");
            return;
        }
        if (argv.helpFlag) {
            System.out.println("Usage: <main class> [-options] class [args...]");
        } else if (argv.versionFlag) {
            System.out.println("java version \"1.8.0\"");
        } else {
            startJVM(argv);
        }
    }

    private static void startJVM(Args args) {
        String mainClassPath = args.getMainClass().replace(".", "/");
        Classpath cp = new Classpath(args.jre, args.classpath);
        System.out.printf("classpath:%s class:%s args:%s\n",
                cp, args.getMainClass(), args.getAppArgs());
        try {
            byte[] classData = cp.readClass(mainClassPath);

            /**
             * 在java中，不支持无符号数。
             * 栈上的数据4字节一个单位，所以byte也是4字节的，按int来处理，高24位用符号位填充。
             */
            System.out.print("class data:[");
            for (byte b : classData) {
                int i=0x000000FF&b;     /* 0|b 是无效的 */
                System.out.print(i+" ");
            }
            System.out.println("]");

//            ClassReader reader = new ClassReader(classData);
//            ClassFile classFile = reader.parseClassFile();
//            MethodInfo mainMethod = classFile.getMainMethod();

            ClassLoader classLoader = ClassLoader.newClassLoader(cp);
            Class mainClass = classLoader.loadClass(mainClassPath);
            Method mainMethod = mainClass.getMainMethod();
            Interpreter.execute(mainMethod);
            System.out.println("class data:" + Arrays.toString(classData));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("could not find or load main class" + args.getMainClass());
        }
    }
}
