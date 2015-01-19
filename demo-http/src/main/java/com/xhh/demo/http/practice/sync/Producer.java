package com.xhh.demo.http.practice.sync;

import java.util.Random;

/**
 * Producer
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-13
 * @since 1.6
 */
public class Producer implements Runnable {

    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
            System.out.format("MESSAGE SENDED: %s%n", importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
            }
        }
        drop.put("DONE");
    }
}
