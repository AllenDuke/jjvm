package com.github.allenduke.jjvm.rtda.heap;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
/**
 * @author allen
 * @description
 * @contact AllenDuke@163.com
 * @date 2021/2/26
 */
public class MethodDescriptor {

    private List<String> parameterTypes=new ArrayList<>();

    private String returnType;

    public void addParameterType(String e){
        parameterTypes.add(e);
    }

}
