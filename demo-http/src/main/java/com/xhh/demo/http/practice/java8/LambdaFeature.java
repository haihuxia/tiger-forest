package com.xhh.demo.http.practice.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * LambdaFeature
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-4
 * @since 1.6
 */
public class LambdaFeature {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

//        Collections.sort(names, (a, b) -> b.compareTo(a));
//        System.out.println(names);
//
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//        Integer converted = converter.convert("123");
//        System.out.println(converted);
    }
}
