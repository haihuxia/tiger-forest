package com.xhh.demo.http.practice.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 夏海虎 on 2014/4/8.
 */
public class ExceptionTest {

    public static void doStuff() throws MyException {
        List<Throwable> list = new ArrayList<Throwable>();
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            list.add(e);
        }

        try {
            int a = 1 / 0;
        } catch (Exception e) {
            list.add(e);
        }

        if (list.size() > 0) {
            throw new MyException(list);
        }
    }

    public static void main(String[] args) {
        try {
            doStuff();
        } catch (MyException e) {
            for (int i = 0; i < e.getException().size(); i++) {
                e.getException().get(i).printStackTrace();
            }
        }

    }
}
