package com.xhh.demo.http.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是对 Java 语言中返回值不参与重载选择的基本认识的挑战？
 *
 * 在 Class 文件格式之中，只要描述符不是完全一致的两个方法就可以共存
 *
 * Created by tiger on 14/11/1.
 */
public class GenericTypes {

    public static String method(List<String> list) {
        System.out.println("invoke method(List<String> list)");
        return null;
    }

    public static int method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }

    public static void main(String[] args) {
        method(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}
