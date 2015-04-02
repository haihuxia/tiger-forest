package com.xhh.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ZookeeperLock {

    private String zkServerUrl = "192.168.106.19:32181";

    private long lockTime = 30;

    private CuratorFramework client = null;

    @PostConstruct
    public void init() {
        try {
            connect();
        } catch (Exception e) {
            log.error("zookeeper connect  error：" , e);
        }
    }

    /**
     * 初始化连接
     */
    private void connect() {
        client = CuratorFrameworkFactory.newClient(zkServerUrl, new ExponentialBackoffRetry(100, 3));
        client.start();
    }

    /**
     *
     * @param path  锁定路径
     * @return
     */
    public InterProcessMutex creatInterProcessMutex(String path){
       return new InterProcessMutex(client, path);
    }

    /**
     * 抢zk锁，重载方法，默认使用配置文件的锁定时间
     * @param lock
     * @return
     */
    public  void acquire(InterProcessMutex lock) {
        long beginTime = new Date().getTime();
        acquire(lock, lockTime);
        long endTime = new Date().getTime();
        if((endTime - beginTime) > 3000){
            log.error("ZookeeperLock timeOut : time {} " , (endTime - beginTime));
        }
    }


    /**
     * 抢zk锁，重载方法，可以自定义锁定时间
     * @param lock 单位(秒)
     * @param lockTime 锁定时间
     * @return
     */
    public void acquire(InterProcessMutex lock, long lockTime) {
        try {
            if(!lock.acquire(lockTime, TimeUnit.SECONDS)){
                log.error("获取锁失败");
            }
        } catch (Exception e) {
            log.error("获取锁失败");
            e.printStackTrace();
        }
    }

    /**
     * 释放zk锁
     * @param lock
     */
    public synchronized  void  release(InterProcessMutex lock) {
        try {
            if(lock != null){
                lock.release();
            }
        } catch (Exception e) {
            log.error("zookeeper lock release fail:", e);
        }
    }

    /**
     * 释放zk锁
     * @param lock
     */
    public synchronized  void  release(InterProcessMutex lock, String basePath) {
        try {
            if (lock != null) {
                lock.release();
                log.debug("delete basePath: {}", basePath);
                client.delete().forPath(basePath);
            }
        } catch (KeeperException e) {
            if (!e.code().name().equals(KeeperException.Code.NOTEMPTY.name())) {
                log.error("zookeeper lock KeeperException fail: {}",e);
            }
        } catch (Exception e) {
            log.error("zookeeper lock release fail: {}",e);
        }
    }

}
