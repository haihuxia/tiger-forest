package com.xhh.demo.zookeeper;

import com.xhh.demo.zookeeper.support.URL;

/**
 * zkClient 连接封装接口
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/13 下午11:23
 * @since 1.6
 */
public interface ZookeeperTransporter {

    /**
     * 建立 zkClient 连接
     *
     * @param url 连接参数
     * @return zkClient 接口
     */
    ZookeeperClient connect(URL url);
}
