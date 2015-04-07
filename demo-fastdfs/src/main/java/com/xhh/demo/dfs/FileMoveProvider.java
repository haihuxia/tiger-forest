package com.xhh.demo.dfs;

import com.xhh.demo.dfs.dal.models.DBModelDO;
import com.xhh.demo.dfs.helper.FileMoveHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /** 文件迁移 helper */
    @Autowired
    private FileMoveHelper fileMoveHelper;

    /** 文件迁移队列 */
    @Autowired
    private FileMoveQueue fileMoveQueue;

    /** 每页条数 */
    private static final int PAGE_SIZE = 300;

    /**
     * 线程入口
     */
    @Override
    public void run() {
        int total = fileMoveHelper.queryCount();
        int pageCount = (total + PAGE_SIZE - 1) / PAGE_SIZE;
        for (int i = 0; i < pageCount; i++) {
            List<DBModelDO> list = fileMoveHelper.queryByPage((i - 1) * PAGE_SIZE, i * PAGE_SIZE);
            for (DBModelDO model : list) {
                fileMoveQueue.put(model);
                log.debug("【put】 model: {}", model.getId());
            }
        }
    }
}
