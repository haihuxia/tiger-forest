package com.xhh.demo.http.practice.factory;

/**
 * CarFactory
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-3
 * @since 1.6
 */
public class CarFactory {

    public static Car createCar (Class<? extends Car> c) {
        try {
            return c.newInstance();
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
