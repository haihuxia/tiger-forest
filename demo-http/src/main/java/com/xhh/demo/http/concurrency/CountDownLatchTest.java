package com.xhh.demo.http.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 闭锁实现 测试
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/24 下午8:24
 * @see java.util.concurrent.CountDownLatch
 * @since 1.6
 */
public class CountDownLatchTest {

    /**
     * 统计消耗时间
     *
     * @param nThreads 线程数
     * @param task 任务
     * @return 执行时间
     * @throws InterruptedException
     */
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            final Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
