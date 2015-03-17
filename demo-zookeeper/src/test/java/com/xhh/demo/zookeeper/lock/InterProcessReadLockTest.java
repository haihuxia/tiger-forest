package com.xhh.demo.zookeeper.lock;

import com.xhh.demo.zookeeper.constant.CommonString;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.TimeUnit;

/**
 * 可读写锁测试
 *
 * <p>一个读写锁管理一对相关的锁。 一个负责读操作，另外一个负责写操作。
 * 读操作在写锁没被使用时可同时由多个进程使用，而写锁使用时不允许读 (阻塞)
 *
 * <p>一个拥有写锁的线程可重入读锁，但是读锁却不能进入写锁
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午3:10
 * @since 1.6
 */
public class InterProcessReadLockTest {

    /** 锁节点 */
    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                CuratorFramework client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR,
                        new ExponentialBackoffRetry(1000, 3));
                client.start();
                final ExampleInterProcessReadWriteLock example = new ExampleInterProcessReadWriteLock(client,
                        PATH, resource, "Client read");
                try {
                    for (int i = 0; i < 50; i++) {
                        example.doRead(10, TimeUnit.SECONDS);
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    CloseableUtils.closeQuietly(client);
                }
            }
        });
        thread.run();
    }
}
