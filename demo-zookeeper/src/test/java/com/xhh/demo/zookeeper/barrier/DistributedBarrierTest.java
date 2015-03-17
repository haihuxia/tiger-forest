package com.xhh.demo.zookeeper.barrier;

import com.xhh.demo.zookeeper.constant.CommonString;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 栅栏
 *
 * <p>阻塞所有节点上等待的线程，直到某一个条件被满足， 然后所有的节点才继续进行
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午8:36
 * @since 1.6
 */
@Slf4j
public class DistributedBarrierTest {

    /** 线程池大小 */
    private static final int COUNT = 5;
    
    /** 锁节点 */
    private static final String PATH = "/examples/barrier";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR, 
                new ExponentialBackoffRetry(1000, 3));
        client.start();
        ExecutorService service = Executors.newFixedThreadPool(COUNT);
        DistributedBarrier controlBarrier = new DistributedBarrier(client, PATH);
        controlBarrier.setBarrier();
        for (int i = 0; i < COUNT; ++i) {
            final DistributedBarrier barrier = new DistributedBarrier(client, PATH);
            final int index = i;
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    Thread.sleep((long) (3 * Math.random()));
                    log.debug("Client #{} waits on Barrier", index);
                    barrier.waitOnBarrier();
                    log.debug("Client #{} begins", index);
                    return null;
                }
            };
            service.submit(task);
        }
        log.debug("all Barrier instances should wait the condition");
        Thread.sleep(5000);
        controlBarrier.removeBarrier();
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
    }
}
