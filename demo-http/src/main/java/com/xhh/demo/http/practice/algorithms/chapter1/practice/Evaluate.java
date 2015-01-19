package com.xhh.demo.http.practice.algorithms.chapter1.practice;

import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

/**
 * 双栈算术表达式求值算法
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-11-23
 * @since 1.6
 */
public class Evaluate {

    public static void calc(String[] expre) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        for (String a : expre) {
            if (StringUtils.isBlank(a)) {
                continue;
            }
            if (a.equals("(")) {
            } else if (a.equals("+")) {
                ops.push(a);
            } else if (a.equals("-")) {
                ops.push(a);
            } else if (a.equals("*")) {
                ops.push(a);
            } else if (a.equals("/")) {
                ops.push(a);
            } else if (a.equals("sqrt")) {
                ops.push(a);
            } else if (a.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) {
                    v = vals.pop() + v;
                } else if (op.equals("-")) {
                    v = vals.pop() - v;
                } else if (op.equals("*")) {
                    v = vals.pop() * v;
                } else if (op.equals("/")) {
                    v = vals.pop() / v;
                } else if (op.equals("sqrt")) {
                    v = Math.sqrt(v);
                }
                vals.push(v);
            } else {
                vals.push(Double.parseDouble(a));
            }
        }
        System.out.println(vals.pop());
    }

    public static void main(String[] args) {
        String a = "(1+((2+3)*(4*5)))";
        System.out.println(a);
        String[] b = a.split("|");
        Evaluate.calc(b);
    }
}
