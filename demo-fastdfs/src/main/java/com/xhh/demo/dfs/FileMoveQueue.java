package com.xhh.demo.dfs;

import com.xhh.demo.dfs.dal.models.DBModelDO;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 文件迁移队列
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/7 上午10:11
 * @since 1.6
 */
@Component
public class FileMoveQueue {

    /** 文件迁移队列 */
    private final ArrayBlockingQueue<DBModelDO> queue = new ArrayBlockingQueue<DBModelDO>(2000);

    /** 存放数据锁对象 */
    private final Object putObj = new Object();

    /** 取出数据锁对象 */
    private final Object takeObj = new Object();

    /**
     * 添加数据
     *
     * @param modle 数据对象
     */
    public void put(DBModelDO modle) {
        synchronized (putObj) {
            try {
                queue.put(modle);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取出数据
     */
    public DBModelDO take() {
        synchronized (takeObj) {
            try {
                return queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
