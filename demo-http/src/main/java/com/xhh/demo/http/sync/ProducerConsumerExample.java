package com.xhh.demo.http.sync;

/**
 * ProducerConsumerExample
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-13
 * @since 1.6
 */
public class ProducerConsumerExample {

    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
