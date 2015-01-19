package com.xhh.demo.http.other;

import java.util.ArrayList;
import java.util.List;

/**
 * jdk1.6 可以通过2个 method 方法
 *
 * <p>这是对 Java 语言中返回值不参与重载选择的基本认识的挑战？
 *
 * <p>在 Class 文件格式之中，只要描述符不是完全一致的两个方法就可以共存
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-11-1
 * @since 1.6
 */
public class GenericTypes {

    public static String method1(List<String> list) {
        System.out.println("invoke method(List<String> list)");
        return null;
    }

    public static int method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }

    public static void main(String[] args) {
        method1(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}
