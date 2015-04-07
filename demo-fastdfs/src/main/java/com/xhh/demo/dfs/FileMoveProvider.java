package com.xhh.demo.dfs;

import com.xhh.demo.dfs.dal.models.DBModelDO;
import com.xhh.demo.dfs.helper.FileMoveHelper;
import com.xhh.demo.dfs.utils.FastDFSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 处理需要迁移的问题
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/7 上午9:53
 * @since 1.6
 */
@Component
@Slf4j
public class FileMoveProvider implements Runnable {

    /** 线程池 */
    private Executor executor = Executors.newFixedThreadPool(6);

    /** fastdfs util */
    @Autowired
    private FastDFSUtil fastDFSUtil;

    /** 文件迁移 helper */
    @Autowired
    private FileMoveHelper fileMoveHelper;

    /** 有界阻塞队列 */
    private final ArrayBlockingQueue<DBModelDO> queue = new ArrayBlockingQueue<DBModelDO>(2000);

    /** 每页条数 */
    private static final int PAGE_SIZE = 2;

    /** 开始迁移 */
    public void moveFile() {
        // 启动消费者
        executor.execute(new FileMoveConsumer(queue, fastDFSUtil, fileMoveHelper));
        // 启动消费者
        executor.execute(new FileMoveConsumer(queue, fastDFSUtil, fileMoveHelper));
        // 启动消费者
        executor.execute(new FileMoveConsumer(queue, fastDFSUtil, fileMoveHelper));
        // 启动生产者
        executor.execute(this);
    }

    /**
     * 线程入口
     */
    @Override
    public void run() {
        int total = fileMoveHelper.queryCount();
        log.debug("【total: {}】", total);
        int pageCount = (total + PAGE_SIZE - 1) / PAGE_SIZE;
        for (int i = 0; i < pageCount; i++) {
            List<DBModelDO> list = fileMoveHelper.queryByPage(i * PAGE_SIZE, (i + 1) * PAGE_SIZE);
            for (DBModelDO aList : list) {
                try {
                    queue.put(aList);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
