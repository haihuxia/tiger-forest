package com.xhh.demo.http.factory;

/**
 * FordCar
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-3
 * @since 1.6
 */
public class FordCar implements Car {

    @Override
    public void desc() {
        System.out.println("---- FordCar ----");
    }
}
