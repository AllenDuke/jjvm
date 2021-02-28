package com.github.allenduke.jjvm.rtda.heap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/28
 */
public class ClassNameHelper {

    private static final Map<String, String> PRIMITIVE_TYPE_MAP = new HashMap<>();

    static {
        PRIMITIVE_TYPE_MAP.put("void", "V");
        PRIMITIVE_TYPE_MAP.put("boolean", "Z");
        PRIMITIVE_TYPE_MAP.put("byte", "B");
        PRIMITIVE_TYPE_MAP.put("short", "S");
        PRIMITIVE_TYPE_MAP.put("int", "I");
        PRIMITIVE_TYPE_MAP.put("long", "J");
        PRIMITIVE_TYPE_MAP.put("char", "C");
        PRIMITIVE_TYPE_MAP.put("float", "F");
        PRIMITIVE_TYPE_MAP.put("double", "D");
    }

    // [XXX -> [[XXX
// int -> [I
// XXX -> [LXXX;
    public static String getArrayClassName(String className) {
        return "[" + toDescriptor(className);
    }

    // [[XXX -> [XXX
// [LXXX; -> XXX
// [I -> int
    public static String getComponentClassName(String className) {
        if (className.charAt(0) == '[') {
            String componentTypeDescriptor = className.substring(1);
            return toClassName(componentTypeDescriptor);
        }
        throw new RuntimeException("Not array: " + className);
    }

    // [XXX => [XXX
    // int  => I
    // XXX  => LXXX;
    public static String toDescriptor(String className) {
        if (className.charAt(0) == '[') {
            // array
            return className;
        }
        String descriptor = PRIMITIVE_TYPE_MAP.get(className);
        if (descriptor != null) return descriptor;
        // object
        return "L" + className + ";";
    }

    // [XXX  => [XXX
    // LXXX; => XXX
    // I     => int
    public static String toClassName(String descriptor) {
        if (descriptor.charAt(0) == '[') {
            // array
            return descriptor;
        }
        if (descriptor.charAt(0) == 'L') {
            // object
            return descriptor.substring(1);
        }
        for (Map.Entry<String, String> entry : PRIMITIVE_TYPE_MAP.entrySet()) {
            if (entry.getValue().equals(descriptor)) return entry.getKey();
        }
        throw new RuntimeException("Invalid descriptor: " + descriptor);
    }
}
