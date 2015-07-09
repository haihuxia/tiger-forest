package com.xhh.demo.http.factory;

/**
 * BenzCar
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-3
 * @since 1.6
 */
public class BenzCar implements Car {

    @Override
    public void desc() {
        System.out.println("---- BenzCar ----");
    }
}
