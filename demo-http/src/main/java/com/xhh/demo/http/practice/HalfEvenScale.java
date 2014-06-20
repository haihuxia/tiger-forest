package com.xhh.demo.http.practice;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by tiger on 2014/4/2.
 */
public class HalfEvenScale {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("10.1244");
        System.out.println("bigDecimal 10.1244 -> " + bigDecimal.setScale(2, RoundingMode.HALF_EVEN));

        bigDecimal = new BigDecimal("10.1254");
        System.out.println("bigDecimal 10.1254 -> " + bigDecimal.setScale(2, RoundingMode.HALF_EVEN));

        bigDecimal = new BigDecimal("10.125");
        System.out.println("bigDecimal 10.125 -> " + bigDecimal.setScale(2, RoundingMode.HALF_EVEN));

        bigDecimal = new BigDecimal("10.135");
        System.out.println("bigDecimal 10.135 -> " + bigDecimal.setScale(2, RoundingMode.HALF_EVEN));
    }
}
