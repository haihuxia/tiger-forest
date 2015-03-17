package com.xhh.demo.zookeeper.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.concurrent.TimeUnit;

/**
 * 类注释
 *
 * <p>请求锁、使用资源、释放锁
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午5:40
 * @see
 * @since 1.6
 */
@Slf4j
public class ExampleInterProcessReadWriteLock {

    /** 读锁 */
    private final InterProcessMutex readLock;

    /** 写锁 */
    private final InterProcessMutex writeLock;

    /** 模拟资源 */
    private final FakeLimitedResource resource;

    /** 客户端名称 */
    private final String clientName;

    /**
     * 构造函数
     *
     * @param client 客户端
     * @param lockPath 锁节点
     * @param resource 资源
     * @param clientName 客户端名称
     */
    public ExampleInterProcessReadWriteLock(CuratorFramework client, String lockPath, FakeLimitedResource resource,
                                             String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        /* 读写互斥锁 */
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, lockPath);
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    /**
     * 请求读锁
     *
     * @param time 抢锁超时时间
     * @param unit 时间单位
     * @throws Exception
     */
    public void doRead(long time, TimeUnit unit) throws Exception {
        if (!readLock.acquire(time, unit)) {
            throw new IllegalStateException("【" + clientName + "】 could not acquire the readLock");
        }
        log.debug("【{}】 has the readLock", clientName);
        try {
            // 使用资源
            resource.use();
        } finally {
            log.debug("【{}】 releasing the lock", clientName);
            // 释放锁
            readLock.release();
        }
    }

    /**
     * 请求读锁
     *
     * @param time 抢锁超时时间
     * @param unit 时间单位
     * @throws Exception
     */
    public void doWrite(long time, TimeUnit unit) throws Exception {
        try {
            Thread.sleep(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getWrite(time, unit);
    }

    /**
     * 请求写锁
     *
     * @param time 抢锁超时时间
     * @param unit 时间单位
     * @throws Exception
     */
    public void getWrite(long time, TimeUnit unit) throws Exception {
        if (!writeLock.acquire(time, unit)) {
            throw new IllegalStateException("【" + clientName + "】 could not acquire the writeLock");
        }
        log.debug("【{}】 has the writeLock", clientName);
        try {
            // 使用资源
            resource.use();

            Thread.sleep(30000);
        } finally {
            log.debug("【{}】 releasing the lock", clientName);
            // 释放锁
            writeLock.release();
        }
    }
}
