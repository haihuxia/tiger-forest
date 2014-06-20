package com.xhh.demo.http.practice.factory;

/**
 * Created by tiger on 2014/4/3.
 */
public class CarFactory {

    public static Car createCar (Class<? extends Car> c) {
        try {
            return (Car)c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Car car = CarFactory.createCar(BenzCar.class);
        car.desc();

        Car c = CarFactory.createCar(FordCar.class);
        c.desc();
    }
}
