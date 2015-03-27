package com.xhh.demo.zookeeper.lock;

import com.xhh.demo.zookeeper.constant.CommonString;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 可重入锁测试
 *
 * <p>同一个客户端在拥有锁的同时，可以多次获取，不会被阻塞
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午3:10
 * @since 1.6
 */
public class InterProcessMutexTest {

    /** 线程池大小 */
    private static final int COUNT = 1;

    /** 重复次数 */
    private static final int REPETITIONS = 1;

    /** 锁节点 */
    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; ++i) {
            final int index = i;
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    CuratorFramework client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR,
                            new ExponentialBackoffRetry(1000, 3));
                    try {
                        client.start();
                        final ExampleInterProcessMutex example = new ExampleInterProcessMutex(client,
                                PATH, resource, "Client " + index);
                        for (int j = 0; j < REPETITIONS; ++j) {
                            example.doWork(10, TimeUnit.SECONDS);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    } finally {
                        CloseableUtils.closeQuietly(client);
                    }
                    return null;
                }
            };
            service.submit(task);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
    }
}
