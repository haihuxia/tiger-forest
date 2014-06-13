package com.xhh.demo.http.practice.sync;

/**
 * Created by tiger on 14-6-13.
 */
public class ProducerConsumerExample {

    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
