package com.xhh.demo.http.factory.enumfactory;

import com.xhh.demo.http.factory.Car;

/**
 * CarAbstractFactory
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-3
 * @since 1.6
 */
public enum CarAbstractFactory {

    BenzCar {
        public Car create() {
            return new com.xhh.demo.http.factory.BenzCar();
        }
    },
    FordCar {
        public Car create() {
            return new com.xhh.demo.http.factory.FordCar();
        }
    };

    public abstract Car create();
}
