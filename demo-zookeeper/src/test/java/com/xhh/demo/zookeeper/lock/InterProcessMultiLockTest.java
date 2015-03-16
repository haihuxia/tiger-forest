package com.xhh.demo.zookeeper.lock;

import com.xhh.demo.zookeeper.constant.CommonString;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 组锁
 *
 * <p>请求释放操作都会传递给它包含的所有的锁
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午8:19
 * @since 1.6
 */
public class InterProcessMultiLockTest {

    /**
     * 锁结点
     */
    private static final String PATH1 = "/examples/locks1";
    private static final String PATH2 = "/examples/locks2";

    public static void main(String[] args) throws Exception {
        FakeLimitedResource resource = new FakeLimitedResource();
        CuratorFramework client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR,
                new ExponentialBackoffRetry(1000, 3));
        client.start();
        InterProcessLock lock1 = new InterProcessMutex(client, PATH1);
        InterProcessLock lock2 = new InterProcessSemaphoreMutex(client, PATH2);
        InterProcessMultiLock lock = new InterProcessMultiLock(Arrays.asList(lock1, lock2));
        if (!lock.acquire(3, TimeUnit.SECONDS)) {
            throw new IllegalStateException("could not acquire the lock");
        }
        System.out.println("has the lock");
        System.out.println("has the lock1: " + lock1.isAcquiredInThisProcess());
        System.out.println("has the lock2: " + lock2.isAcquiredInThisProcess());
        try {
            resource.use();
        } finally {
            System.out.println("releasing the lock");
            lock.release();
        }
        System.out.println("has the lock1: " + lock1.isAcquiredInThisProcess());
        System.out.println("has the lock2: " + lock2.isAcquiredInThisProcess());
    }
}
