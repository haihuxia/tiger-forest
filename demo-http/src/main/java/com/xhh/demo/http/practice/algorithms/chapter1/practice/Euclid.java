package com.xhh.demo.http.practice.algorithms.chapter1.practice;

/**
 * 欧几里得算法
 *
 * 计算两个非负整数 p 和 q 的最大公约数：诺 q 是0，则最大公约数为 p。否则将 p 除以 q 得到余数 r，p 和 q 的最大公约数即为 q 和 r 的最大公约数。
 *
 * Created by tiger on 14/11/16.
 */
public class Euclid {

    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        Euclid.gcd(4, 5);
    }
}
