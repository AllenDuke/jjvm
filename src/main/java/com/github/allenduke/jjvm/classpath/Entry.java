package com.github.allenduke.jjvm.classpath;

import java.io.File;

public interface Entry {

    /**
     * @param className 全限定名
     * @description: 寻找并加载class文件
     * @return: byte[]
     * @author: allen
     * @date: 2021/2/11
     */
    byte[] readClass(String className) throws Exception;

    /** 依据传入的路径建造各种Entry
     * @param path
     * @description:
     * @return: com.github.allenduke.avm.classpath.Entry
     * @author: allen
     * @date: 2021/2/11
     */
    static Entry create(String path) {

        if (path.contains(File.pathSeparator)) {    /* 包含分割符 :（linux） 或者 ;（win） */
            return new CompositeEntry(path);
        }

        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }

        if (path.endsWith(".jar") || path.endsWith(".JAR") ||
                path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }

        return new DirEntry(path);
    }

}
