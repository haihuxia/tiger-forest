package com.xhh.demo.http.practice.factory.enumfactory;

import com.xhh.demo.http.practice.factory.Car;

/**
 * Created by 夏海虎 on 2014/4/3.
 */
public enum CarAbstractFactory {

    BenzCar {
        public Car create() {
            return new com.xhh.demo.http.practice.factory.BenzCar();
        }
    },
    FordCar {
        public Car create() {
            return new com.xhh.demo.http.practice.factory.FordCar();
        }
    };

    public abstract Car create();
}
