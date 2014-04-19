package com.xhh.demo.http.practice;

import java.util.Random;

/**
 * Created by 夏海虎 on 14-3-24.
 */
public class DynamicConst {

    public static void main(String[] args) {
        System.out.println("RAND_CONST's value is: " + Const.RAND_CONST);
    }

    interface Const {
        public static final int RAND_CONST = new Random().nextInt();
    }

}
