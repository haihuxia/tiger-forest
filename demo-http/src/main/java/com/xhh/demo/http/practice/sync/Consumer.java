package com.xhh.demo.http.practice.sync;

import java.util.Random;

/**
 * Consumer
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-13
 * @since 1.6
 */
public class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (String message = drop.take();
             ! message.equals("DONE");
             message = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            System.out.println();
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
    }
}
