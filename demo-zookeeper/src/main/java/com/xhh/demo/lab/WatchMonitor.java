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
 * 监控 lab 目录下增、删、改的节点
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

    /** 监控结点 */
    public static final String PATH = "/app1";

    /** 监控结点子结点列表 */
    private static List<String> nodeList;

    public static void main(String[] args) {
        Thread thread = new Thread(new WatchMonitor());
        thread.start();
    }

    WatchMonitor () {
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
                    log.debug("----------- connectionState.name: {}", connectionState.name());
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
                // 结点数据改变之前的结点列表
                List<String> nodeListBefore = nodeList;
                // 主结点的数据发生改变时
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeDataChanged) {
                    log.info("Node data changed:" + watchedEvent.getPath());
                }
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted){
                    log.info("Node deleted:" + watchedEvent.getPath());
                }
                if(watchedEvent.getType()== Watcher.Event.EventType.NodeCreated){
                    log.info("Node created:"+watchedEvent.getPath());
                }

                // 获取更新后的nodelist
                try {
                    nodeList = zkClient.getChildren().forPath(watchedEvent.getPath());
                } catch (KeeperException e) {
                    System.out.println(watchedEvent.getPath()+" has no child, deleted.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<String> nodeListNow = nodeList;
                // 增加结点
                if (nodeListBefore.size() < nodeListNow.size()) {
                    for (String str : nodeListNow) {
                        if (!nodeListBefore.contains(str)) {
                            log.info("Node created:" + watchedEvent.getPath() + "/" + str);
                        }
                    }
                }
            }
        };

        /**
         * 持续结点监控
         */
        while (true) {
            try {
                // 监控主结点
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
            // 对PATH下的每个结点都设置一个watcher
            for (String nodeName : nodeList) {
                try {
                    zkClient.checkExists().usingWatcher(wc).forPath(PATH + "/" + nodeName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // sleep一会，减少CUP占用率
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
