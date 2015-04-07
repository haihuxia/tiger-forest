package com.xhh.demo.dfs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文件从 nfs 迁移至 fastdfs
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/7 上午8:48
 * @since 1.6
 */
@Service
public class FileFromNfsToFastdfsServer {

    @Autowired
    private FileMoveProvider provider;

    @Autowired
    private FileMoveConsumer consumer;

    public static void main(String[] args) {
        new Thread();
    }
}
