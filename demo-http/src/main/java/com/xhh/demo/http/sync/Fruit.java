package com.xhh.demo.http.sync;

import com.xhh.demo.http.core.Fruits;

import java.util.List;

/**
 * Fruit
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-12
 * @since 1.6
 */
public class Fruit {

    /**
     * 为虚拟对象锁提供条件
     */
    private Object vmObject = new Object();

    public void list1() {
        synchronized (vmObject) {
            List<String> list = Fruits.getFruits();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(Thread.currentThread().getName() + " - " + list.get(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void list2() {
        synchronized (this) {
            List<String> list = Fruits.getFruits();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(Thread.currentThread().getName() + " & " + list.get(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
