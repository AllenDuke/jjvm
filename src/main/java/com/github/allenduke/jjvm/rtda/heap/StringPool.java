package com.github.allenduke.jjvm.rtda.heap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author allen
 * @description 字符串池 todo 优化String的处理
 * @contact AllenDuke@163.com
 * @date 2021/2/28
 */
public class StringPool {

    private static final Map<String,AObject> INTERNED_STRING_MAP=new HashMap();

    public static AObject JString(ClassLoader loader, String str){
        AObject internedStr = INTERNED_STRING_MAP.get(str);
        if(internedStr!=null) return internedStr;

        AObject jChars = new AObject();
        jChars.setClazz(loader.loadClass("[C"));
        jChars.setData(str.getBytes());

        AObject jStr = loader.loadClass("java/lang/String").newObject();
        jStr.setRefVar("value", "[C", jChars);

        INTERNED_STRING_MAP.put(str,jStr);
        return jStr;
    }

    public static String String(AObject jStr){
        AObject charArr = jStr.getRefVar("value", "[C");
        byte[] bytes = charArr.bytes();     /* 这里用bytes，因为存进去的时候是用bytes */
        return new String(bytes);
    }
}
