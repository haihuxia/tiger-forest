package com.xhh.demo.http.practice.factory.enumfactory;

import com.xhh.demo.http.practice.factory.Car;

/**
 * Factory
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-3
 * @since 1.6
 */
public class Factory {

    public static void main(String[] args) {
        Car benzCar = CarFactory.BenzCar.create();
        benzCar.desc();

        Car fordCar = CarFactory.FordCar.create();
        fordCar.desc();

        Car c1 = CarAbstractFactory.BenzCar.create();
        c1.desc();

        Car c2 = CarAbstractFactory.FordCar.create();
        c2.desc();


    }
}
