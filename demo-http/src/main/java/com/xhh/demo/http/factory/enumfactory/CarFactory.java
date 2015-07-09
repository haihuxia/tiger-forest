package com.xhh.demo.http.factory.enumfactory;

import com.xhh.demo.http.factory.Car;

/**
 * CarFactory
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-3
 * @since 1.6
 */
public enum CarFactory {

    BenzCar, FordCar;

    public Car create(){
        switch (this) {
            case BenzCar:
                return new com.xhh.demo.http.factory.BenzCar();
            case FordCar:
                return new com.xhh.demo.http.factory.FordCar();
            default:
                throw new AssertionError("无效参数");
        }
    }
}
