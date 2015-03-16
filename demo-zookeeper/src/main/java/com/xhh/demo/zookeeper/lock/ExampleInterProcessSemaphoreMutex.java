package com.xhh.demo.zookeeper.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import java.util.concurrent.TimeUnit;

/**
 * 不可重入锁测试
 *
 * <p>请求锁、使用资源、释放锁
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/16 下午5:23
 * @see org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex
 * @since 1.6
 */
@Slf4j
public class ExampleInterProcessSemaphoreMutex {

    /** 不可重入锁 */
    private final InterProcessSemaphoreMutex lock;

    /** 模拟资源 */
    private final FakeLimitedResource resource;

    /** 客户端名称 */
    private final String clientName;

    /**
     * 构造函数
     *
     * @param client 客户端
     * @param lockPath 锁结点
     * @param resource 资源
     * @param clientName 客户端名称
     */
    public ExampleInterProcessSemaphoreMutex(CuratorFramework client, String lockPath, FakeLimitedResource resource,
                                    String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessSemaphoreMutex(client, lockPath);
    }

    /**
     * 请求锁
     *
     * @param time 抢锁超时时间
     * @param unit 时间单位
     * @throws Exception
     */
    public void doWork(long time, TimeUnit unit) throws Exception {
        log.debug("start to doWork...");
        if (!lock.acquire(time, unit)) {
            throw new IllegalStateException("【" + clientName + "】 could not acquire the lock");
        }
        log.debug("acquire success");
        log.debug("acquire again...");
        if (!lock.acquire(time, unit)) {
            throw new IllegalStateException("【" + clientName + "】 could not acquire the lock.....");
        }
        try {
            log.debug("【{}】 has the lock", clientName);
            // 使用资源
            resource.use();
        } finally {
            log.debug("【{}】 releasing the lock", clientName);
            // 释放锁
            lock.release();
            lock.release();
        }
    }
}
