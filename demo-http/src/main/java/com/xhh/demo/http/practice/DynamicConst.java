package com.xhh.demo.http.practice;

import java.util.Random;

/**
 * DynamicConst
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-3-24
 * @since 1.6
 */
public class DynamicConst {

    public static void main(String[] args) {
        System.out.println("RAND_CONST's value is: " + Const.RAND_CONST);
    }

    interface Const {
        public static final int RAND_CONST = new Random().nextInt();
    }

}
