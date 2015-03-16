package com.xhh.demo.zookeeper.lock;

import com.xhh.demo.zookeeper.constant.CommonString;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.TimeUnit;

/**
 * 不可重入锁测试
 *
 * <p>获取到锁以后，不可再次获取锁
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午5:27
 * @since 1.6
 */
public class InterProcessSemaphoreMutexTest {

    /** 重复次数 */
    private static final int REPETITIONS = 5;

    /** 锁结点 */
    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        CuratorFramework client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR,
                new ExponentialBackoffRetry(1000, 3));
        try {
            client.start();
            final ExampleInterProcessSemaphoreMutex example = new ExampleInterProcessSemaphoreMutex(client,
                    PATH, resource, "Client ");
            for (int j = 0; j < REPETITIONS; ++j) {
                example.doWork(10, TimeUnit.SECONDS);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }
}
