package com.xhh.demo.http.practice.factory.enumfactory;

import com.xhh.demo.http.practice.factory.Car;

/**
 * Created by 夏海虎 on 2014/4/3.
 */
public enum CarFactory {

    BenzCar, FordCar;

    public Car create(){
        switch (this) {
            case BenzCar:
                return new com.xhh.demo.http.practice.factory.BenzCar();
            case FordCar:
                return new com.xhh.demo.http.practice.factory.FordCar();
            default:
                throw new AssertionError("无效参数");
        }
    }
}
