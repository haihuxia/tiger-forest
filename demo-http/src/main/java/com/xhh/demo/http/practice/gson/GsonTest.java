package com.xhh.demo.http.practice.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Gson 测试 demo
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-13
 * @since 1.6
 */
public class GsonTest {

    private static Map<String, String> parseData(String data){
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        return g.fromJson(data, new TypeToken<Map<String, String>>() {}.getType());
    }

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

        String jsonData = "{'name':'yang','age':2}";
        Map<String,String> map = parseData(jsonData);
        System.out.println("map: " + map);
    }
}
