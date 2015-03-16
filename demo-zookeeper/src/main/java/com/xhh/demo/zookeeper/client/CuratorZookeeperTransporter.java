package com.xhh.demo.zookeeper.client;

import com.xhh.demo.zookeeper.ZookeeperClient;
import com.xhh.demo.zookeeper.ZookeeperTransporter;
import com.xhh.demo.zookeeper.client.CuratorZookeeperClient;
import com.xhh.demo.zookeeper.support.URL;

/**
 * zkClient 连接封装接口实现
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/13 下午11:33
 * @see com.xhh.demo.zookeeper.ZookeeperTransporter
 * @since 1.6
 */
public class CuratorZookeeperTransporter implements ZookeeperTransporter {

    /**
     * 建立 zkClient 连接
     *
     * @param url 连接参数
     * @return zkClient 接口
     */
    @Override
    public ZookeeperClient connect(URL url) {
        return new CuratorZookeeperClient(url);
    }
}
