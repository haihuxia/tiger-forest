package com.xhh.demo.http.practice.gson;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 夏海虎 on 2014/4/13.
 */
public class GsonTest {

    public static void main(String[] args) {
        String a = "#10#20#30#50#100#200#300#500#";
        String[] as = a.split("#");
        for(String b : as) {
            System.out.println("as's elements:" + b);
        }

        List<String> list = Arrays.asList(as);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(i + "  " + list.get(i));
        }

        Long b = Long.parseLong("0.01");
        System.out.println(b);
    }
}
