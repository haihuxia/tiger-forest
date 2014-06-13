package com.xhh.demo.http.practice.annotation;

/**
 * Created by tiger on 14-6-13.
 */
public class Orange {

    @FruitName("Sweet Orange")
    public String name;

    public void printName() throws NoSuchFieldException{
        FruitName fruitName = Orange.class.getDeclaredField("name").getAnnotation(FruitName.class);
        String value = fruitName.value();
        System.out.println(value);
    }

    public static void main(String[] args) throws NoSuchFieldException{
        Orange orange = new Orange();
        orange.printName();
    }
}
