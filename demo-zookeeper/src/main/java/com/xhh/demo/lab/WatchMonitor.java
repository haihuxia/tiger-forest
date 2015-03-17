package com.xhh.demo.lab;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.ArrayList;
import java.util.List;

/**
 * 监控 path 目录下节点的增、删、改
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/12 下午11:59
 * @since 1.6
 */
@Slf4j
public class WatchMonitor implements Runnable {

    /** curator zkClient */
    private static CuratorFramework zkClient = null;

    /** zkConnectionString */
    private final static String ZK_CONN = "192.168.106.102:2181";

    /** 监控节点 */
    public static final String PATH = "/app1";

    /** 监控节点子节点列表 */
    private static List<String> nodeList;

    public static void main(String[] args) {
        Thread thread = new Thread(new WatchMonitor());
        thread.start();
    }

    public WatchMonitor () {
        zkClient = CuratorFrameworkFactory.builder().connectString(ZK_CONN)
                .sessionTimeoutMs(30000)
                .connectionTimeoutMs(30000)
                .canBeReadOnly(false)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("cluster")
                .defaultData(null)
                .build();
        try {
            zkClient.getConnectionStateListenable().addListener(new ConnectionStateListener() {
                @Override
                public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                    log.debug("【connectionState.name: {}】", connectionState.name());
                }
            });
            zkClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        CuratorWatcher wc = new CuratorWatcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                // 节点数据改变之前的节点列表
                List<String> nodeListBefore = nodeList;
                // 主节点的数据发生改变时
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeDataChanged) {
                    log.debug("Node data changed:" + watchedEvent.getPath());
                }
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted){
                    log.debug("Node deleted:" + watchedEvent.getPath());
                }
                if(watchedEvent.getType()== Watcher.Event.EventType.NodeCreated){
                    log.debug("Node created:"+watchedEvent.getPath());
                }

                // 获取更新后的nodelist
                try {
                    nodeList = zkClient.getChildren().forPath(watchedEvent.getPath());
                } catch (KeeperException e) {
                    log.debug(watchedEvent.getPath()+" has no child, deleted.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<String> nodeListNow = nodeList;
                // 增加节点
                if (nodeListBefore.size() < nodeListNow.size()) {
                    for (String str : nodeListNow) {
                        if (!nodeListBefore.contains(str)) {
                            log.debug("Node created:" + watchedEvent.getPath() + "/" + str);
                        }
                    }
                }
            }
        };

        /**
         * 持续节点监控
         */
        while (true) {
            try {
                // 监控主节点
                zkClient.checkExists().usingWatcher(wc);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null == zkClient.checkExists().forPath(PATH)) {
                    nodeList = new ArrayList<String>();
                } else {
                    nodeList = zkClient.getChildren().usingWatcher(wc).forPath(PATH);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 对PATH下的每个节点都设置一个watcher
            for (String nodeName : nodeList) {
                try {
                    zkClient.checkExists().usingWatcher(wc).forPath(PATH + "/" + nodeName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 减少CUP占用率
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
