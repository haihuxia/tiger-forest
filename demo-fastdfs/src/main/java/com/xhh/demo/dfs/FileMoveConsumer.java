package com.xhh.demo.dfs;

import com.xhh.demo.dfs.dal.models.DBModelDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 添加需要迁移的文件
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/7 上午9:53
 * @since 1.6
 */
@Component
@Slf4j
public class FileMoveConsumer implements Runnable {

    @Autowired
    private FileMoveQueue queue;

    /**
     * 线程入口
     */
    @Override
    public void run() {
        DBModelDO model = null;
        while (true) {
            model = queue.take();
            // 开始处理
            log.debug("【take】【{}】 model: {}", model.getId(), Thread.currentThread().getName());
        }
    }
}
