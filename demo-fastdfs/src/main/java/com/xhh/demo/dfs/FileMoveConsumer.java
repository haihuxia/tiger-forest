package com.xhh.demo.dfs;

import com.xhh.demo.dfs.dal.mapper.DBDataMapper;
import com.xhh.demo.dfs.dal.models.DBModelDO;
import com.xhh.demo.dfs.utils.FastDFSUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 添加需要迁移的文件
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/7 上午9:53
 * @since 1.6
 */
@Slf4j
public class FileMoveConsumer implements Runnable {

    /** 有界阻塞队列 */
    private final ArrayBlockingQueue<DBModelDO> queue;

    /** fastdfs util */
    private final FastDFSUtil fastDFSUtil;

    /** 数据操作 mapper */
    private final DBDataMapper dbDataMapper;

    /** nfs 路径 */
    private final static String NFS_PATH = "/NFiles/upload/realname/";

    /**
     * 构造函数
     *
     * @param queue 队列
     */
    public FileMoveConsumer(ArrayBlockingQueue<DBModelDO> queue, FastDFSUtil fastDFSUtil,
                            DBDataMapper dbDataMapper) {
        this.queue = queue;
        this.fastDFSUtil = fastDFSUtil;
        this.dbDataMapper = dbDataMapper;
    }

    /**
     * 线程入口
     */
    @Override
    public void run() {
        DBModelDO model;
        while (true) {
            try {
                if (queue.isEmpty()) {
                    log.debug("【{}】Wait 3 seconds to continue!", Thread.currentThread().getName());
                    Thread.sleep(3000);
                    continue;
                }
                model = queue.take();
                String nfsFilePath = NFS_PATH + model.getFileName();
                File file = new File(nfsFilePath);
                // 检查文件是否存在
                if (!file.exists()) {
                    log.error("File is not exist, {}", nfsFilePath);
                    continue;
                }
                // 开始处理
                Map<String, String> map = fastDFSUtil.uploadByPath(nfsFilePath);
                // 更新数据
                dbDataMapper.update(model.getId(), map.get("REMOTE_FILE_NAME"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
