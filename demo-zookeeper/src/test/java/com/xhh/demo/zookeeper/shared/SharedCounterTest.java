package com.xhh.demo.zookeeper.shared;

import com.google.common.collect.Lists;
import com.xhh.demo.zookeeper.constant.CommonString;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 计数器
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午9:12
 * @since 1.6
 */
@Slf4j
public class SharedCounterTest implements SharedCountListener {

    /** 线程池大小 */
    private static final int COUNT = 5;

    /** 锁节点 */
    private static final String PATH = "/examples/counter";

    public static void main(String[] args) throws Exception {
        final Random rand = new Random();
        SharedCounterTest example = new SharedCounterTest();
        CuratorFramework client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR,
                new ExponentialBackoffRetry(1000, 3));
        client.start();
        SharedCount baseCount = new SharedCount(client, PATH, 0);
        baseCount.addListener(example);
        baseCount.start();
        List<SharedCount> examples = Lists.newArrayList();
        ExecutorService service = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; ++i) {
            final SharedCount count = new SharedCount(client, PATH, 0);
            examples.add(count);
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    count.start();
                    Thread.sleep(rand.nextInt(10000));
                    log.debug("Increment: {}",
                            count.trySetCount(count.getVersionedValue(), count.getCount() + rand.nextInt(10)));
                    return null;
                }
            };
            service.submit(task);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        for (int i = 0; i < COUNT; ++i) {
            examples.get(i).close();
        }
        baseCount.close();
    }

    @Override
    public void stateChanged(CuratorFramework arg0, ConnectionState arg1) {
        log.debug("State changed: {}", arg1.toString());
    }

    @Override
    public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
        log.debug("Counter's value is changed to {}", newCount);
    }
}
