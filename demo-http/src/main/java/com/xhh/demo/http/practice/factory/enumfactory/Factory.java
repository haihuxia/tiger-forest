package com.xhh.demo.http.practice.factory.enumfactory;

import com.xhh.demo.http.practice.factory.Car;

/**
 * Created by 夏海虎 on 2014/4/3.
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
