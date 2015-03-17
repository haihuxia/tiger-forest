package com.xhh.demo.zookeeper.client;

import com.xhh.demo.zookeeper.ChildListener;
import com.xhh.demo.zookeeper.StateListener;
import com.xhh.demo.zookeeper.ZookeeperClient;
import com.xhh.demo.zookeeper.support.URL;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * zkClient 监控
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/14 上午12:38
 * @since 1.6
 */
@Slf4j
public class ZkClientMonitor implements Runnable {

    /** zkClient */
    private ZookeeperClient zkClient = null;

    /** 监听目录 */
    private final static String PATH = "/test";

    /**
     * 初始化 zkClient
     */
    ZkClientMonitor () {
        URL url = new URL("localhost", 2181);
        zkClient = new CuratorZookeeperTransporter().connect(url);
        // 增加状态监听
        zkClient.addStateListener(new StateListener() {
            @Override
            public void stateChanged(int connected) {
                if (StateListener.CONNECTED == connected) {
                    log.debug("【StateListener.CONNECTED】");
                } else if (StateListener.DISCONNECTED == connected) {
                    log.debug("【StateListener.DISCONNECTED】");
                } else if (StateListener.RECONNECTED == connected) {
                    log.debug("【StateListener.RECONNECTED】");
                }
            }
        });
        // 增加子节点监听
        zkClient.addChildListener(PATH, new ChildListener() {
            @Override
            public void childChanged(String path, List<String> children) {
                String node = "path: 【" + path + "】, children: 【";
                for (String child : children) {
                    node += child + ", ";
                }
                if (children.size() > 0) {
                    node = node.substring(0, node.length() - 2);
                }
                log.debug(node + "】");
            }
        });
    }

    @Override
    public void run() {
        // 阻止线程退出
        while (true) {
            try {
                // 减少 CPU 占用率
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ZkClientMonitor());
        thread.start();
    }
}
