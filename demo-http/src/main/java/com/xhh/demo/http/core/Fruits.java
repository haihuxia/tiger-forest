package com.xhh.demo.http.core;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 测试准备数据
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-12
 * @since 1.6
 */
public class Fruits {

    public static List<String> getFruits() {
        List<String> list = Lists.newArrayListWithCapacity(5);
        list.add("apple");          //苹果
        list.add("watermelon");     //西瓜
        list.add("mango");          //芒果
        list.add("orange");         //橘子
        return list;
    }

    public static void main(String[] args) {
        System.out.println(Integer.getInteger("curator-default-session-timeout", '\uea60').intValue());
    }

}
