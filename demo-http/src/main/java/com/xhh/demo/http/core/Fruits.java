package com.xhh.demo.http.core;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by tiger on 14-6-12.
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

}
