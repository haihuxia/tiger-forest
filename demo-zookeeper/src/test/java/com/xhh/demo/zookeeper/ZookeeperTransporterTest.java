package com.xhh.demo.zookeeper;

import com.xhh.demo.zookeeper.curator.CuratorZookeeperTransporter;
import com.xhh.demo.zookeeper.support.URL;

/**
 * ZookeeperTransporter 测试
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/14 上午12:32
 * @since 1.6
 */
public class ZookeeperTransporterTest {

    public static void main(String[] args) {
        URL url = new URL("localhost", 2181);
        ZookeeperClient zkClient = new CuratorZookeeperTransporter().connect(url);
        // 创建持久结点
        zkClient.create("/test", false);
        // 创建临时结点
        zkClient.create("/test/a", true);
        // 删除结点
        zkClient.delete("/test/a");

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        zkClient.close();
    }
}
