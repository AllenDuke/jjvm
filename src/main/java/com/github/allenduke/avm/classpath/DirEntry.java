package com.github.allenduke.avm.classpath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DirEntry implements Entry {

    private Path absPath;

    DirEntry(String path) {
        /**
         * 如果path 是 ""，则使用当前项目所在目录的绝对路径。
         * 否则就是path
         */
        absPath = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
//        System.out.println(className+ " abs:"+absPath);
        return Files.readAllBytes(absPath.resolve(className)); /* 拼接得到class文件绝对路径 */
    }

}
