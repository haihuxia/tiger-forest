package com.xhh.demo.zookeeper.shared;

import com.google.common.collect.Lists;
import com.xhh.demo.zookeeper.constant.CommonString;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Long类型的计数器
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午9:28
 * @since 1.6
 */
@Slf4j
public class DistributedAtomicLongTest {

    /** 线程池大小 */
    private static final int COUNT = 5;

    /** 锁结点 */
    private static final String PATH = "/examples/counter1";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR,
                new ExponentialBackoffRetry(1000, 3));
        client.start();
        List<DistributedAtomicLong> examples = Lists.newArrayList();
        ExecutorService service = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; ++i) {
            final DistributedAtomicLong count = new DistributedAtomicLong(client, PATH, new RetryNTimes(10, 10));
            examples.add(count);
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    try {
                        // Thread.sleep(rand.nextInt(1000));
                        AtomicValue<Long> value = count.increment();
                        // AtomicValue<Long> value = count.decrement();
                        // AtomicValue<Long> value = count.add((long)rand.nextInt(20));
                        log.debug("succeed: {}", value.succeeded());
                        if (value.succeeded()) {
                            log.debug("Increment: from {} to {}", value.preValue(), value.postValue());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
