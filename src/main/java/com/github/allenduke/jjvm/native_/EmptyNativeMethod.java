package com.github.allenduke.jjvm.native_;

import com.github.allenduke.jjvm.rtda.Frame;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/3/1
 */
public class EmptyNativeMethod implements NativeMethod{
    @Override
    public void invoke(Frame frame) {
        //do nothing
    }
}
