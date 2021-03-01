package com.github.allenduke.jjvm.native_;

import com.github.allenduke.jjvm.rtda.Frame;

/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/3/1
 */
public interface NativeMethod {

    void invoke(Frame frame);
}
