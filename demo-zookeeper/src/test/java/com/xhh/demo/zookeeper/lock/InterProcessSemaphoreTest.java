package com.xhh.demo.zookeeper.lock;

import com.xhh.demo.zookeeper.constant.CommonString;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * 信号量测试
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午8:08
 * @since 1.6
 */
@Slf4j
public class InterProcessSemaphoreTest {

    /** 最大租约数 */
    private static final int MAX_LEASE = 10;

    /** 锁节点 */
    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {
        FakeLimitedResource resource = new FakeLimitedResource();
        CuratorFramework client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR,
                new ExponentialBackoffRetry(1000, 3));
        client.start();
        InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(client, PATH, MAX_LEASE);
        Collection<Lease> leases = semaphore.acquire(5);
        log.debug("get " + leases.size() + " leases");
        Lease lease = semaphore.acquire();
        log.debug("get another lease");
        resource.use();
        Collection<Lease> leases2 = semaphore.acquire(5, 3, TimeUnit.SECONDS);
        log.debug("Should timeout and acquire return " + leases2);
        log.debug("return one lease");
        semaphore.returnLease(lease);
        log.debug("return another 5 leases");
        semaphore.returnAll(leases);
    }
}
