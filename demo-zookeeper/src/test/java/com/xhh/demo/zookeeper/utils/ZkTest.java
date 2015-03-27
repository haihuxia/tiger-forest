package com.xhh.demo.zookeeper.utils;

import com.xhh.demo.utils.ZookeeperLock;
import com.xhh.demo.zookeeper.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 单元测试
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/25 下午5:12
 * @since 1.6
 */
@Slf4j
public class ZkTest extends BaseTest {

    @Autowired
    private ZookeeperLock zookeeperLock;

    /** 线程池大小 */
    private static final int THREAD_COUNT = 5;

    /** 锁空间 */
    private static final int LOCK_COUNT = 5;

    @Test
    public void nodeTest() {
        try {
            ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);
            for (int i = 0; i < THREAD_COUNT; ++i) {
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (int i = 0; i < LOCK_COUNT; i++) {
                            InterProcessMutex lock = null;
                            String path = null;
                            try {
                                path = "/lock" + System.nanoTime();
                                log.debug("path: {}", path);
                                lock = zookeeperLock.creatInterProcessMutex(path);
                                zookeeperLock.acquire(lock);
                                Thread.sleep(1000);
                            } catch (Throwable e) {
                                e.printStackTrace();
                            } finally {
                                zookeeperLock.release(lock, path);
                            }
                        }
                        return null;
                    }
                };
                service.submit(task);
            }
            Thread.sleep(6000);
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
