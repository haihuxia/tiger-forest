package com.xhh.demo.http.practice.sync;

/**
 * FavoriteFruit
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-12
 * @since 1.6
 */
public class FavoriteFruit{

    public static void main(String[] args) {
        final Fruit fruit = new Fruit();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                fruit.list1();
            }
        }, "thread-1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                fruit.list2();
            }
        }, "thread-2");

        thread1.start();
        thread2.start();
    }
}
