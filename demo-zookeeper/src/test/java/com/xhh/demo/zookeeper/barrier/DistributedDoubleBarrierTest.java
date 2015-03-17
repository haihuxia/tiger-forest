package com.xhh.demo.zookeeper.barrier;

import com.xhh.demo.zookeeper.constant.CommonString;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 双栅栏
 *
 * <p>当 enter 方法被调用时，成员被阻塞，直到所有的成员都调用了 enter。
 * 当 leave 方法被调用时，它也阻塞调用线程，直到所有的成员都调用了 leave。
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午8:46
 * @since 1.6
 */
@Slf4j
public class DistributedDoubleBarrierTest {

    /** 线程池大小 */
    private static final int COUNT = 5;

    /** 锁结点 */
    private static final String PATH = "/examples/barrier";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR, 
                new ExponentialBackoffRetry(1000, 3));
        client.start();
        ExecutorService service = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; ++i) {
            final DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, PATH, COUNT);
            final int index = i;
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    Thread.sleep((long) (3 * Math.random()));
                    log.debug("Client #{} enters", index);
                    barrier.enter();
                    log.debug("Client #{} begins", index);
                    Thread.sleep(3000);
                    barrier.leave();
                    log.debug("Client #{} left", index);
                    return null;
                }
            };
            service.submit(task);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
    }
}
