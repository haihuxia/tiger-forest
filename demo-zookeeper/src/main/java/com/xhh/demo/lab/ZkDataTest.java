package com.xhh.demo.lab;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.*;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.File;
import java.util.List;

/**
 * zk 测试入口
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/11 下午5:55
 * @since 1.6
 */
@Slf4j
public class ZkDataTest {

    /** curator zkClient */
    private static CuratorFramework zkClient = null;

    /** 连接字符创 */
    private final static String CONNECT_STR = "192.168.106.19:32181";

    /** 节点 */
    public static final String PATH = "/app1";

    public static void main(String[] args) throws InterruptedException {
        zkClient = CuratorFrameworkFactory.builder().connectString(CONNECT_STR)
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

            zkClient.getCuratorListenable().addListener(new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework curatorFramework,
                                          CuratorEvent curatorEvent) throws Exception {
                    log.debug("【curatorEvent.type: {}】", curatorEvent.getType().name());
                }
            });
            zkClient.start();
            test();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Thread.sleep(5000);
            CloseableUtils.closeQuietly(zkClient);
            log.debug("zkClient stop!");
        }
    }

    public static void test() throws Exception {
        ZkDataTest zkDataTest = new ZkDataTest();
//        zkDataTest.getChildren(PATH);
//        if (zkDataTest.checkExist(PATH)) {
//            zkDataTest.delete(PATH);
//        }
//        zkDataTest.createrOrUpdate(PATH + "/a", "aaaa");
        log.debug("namespace: {}", zkClient.getNamespace());
        log.debug("start to getListChildren");
//        zkDataTest.createrOrUpdate(PATH + "/a", "cccc");
//        zkDataTest.createEphemeral("/aa");
//        zkDataTest.createrOrUpdate(PATH + "/b", "1111");
//        zkDataTest.createrOrUpdate("/app3/aa", "aa");
        //zkDataTest.createEphemeral("/app3/aa");
        zkClient.create().withMode(CreateMode.EPHEMERAL).forPath("/app3/aa");
        Thread.sleep(3000);
        zkClient.delete().forPath("/app3");
        //zkDataTest.delete("/app3");
//        zkDataTest.createrOrUpdate(PATH + "/c", "2222");
//        zkDataTest.delete(PATH + "/a");
//        zkDataTest.delete(PATH + "/b");
    }

    /**
     * 创建或更新一个节点
     *
     * @param path 路径
     * @param data 数据
     * @throws Exception
     */
    public void createrOrUpdate(String path, String data) throws Exception {
        zkClient.newNamespaceAwareEnsurePath(path).ensure(zkClient.getZookeeperClient());
        zkClient.setData().forPath(path, data.getBytes());
        log.debug("createOrUpdate, path: {}, data: {}", path, data);
    }

    /**
     * 创建持久节点
     *
     * @param path 路径
     * @throws Exception
     */
    public void createPersistent(String path) throws Exception {
        zkClient.create().forPath(path);
    }

    /**
     * 创建临时节点
     *
     * @param path 路径
     * @throws Exception
     */
    public void createEphemeral(String path) throws Exception {
        zkClient.create().withMode(CreateMode.EPHEMERAL).forPath(path);
    }

    /**
     * 删除一个节点路径
     *
     * @param path 路径
     * @throws Exception
     */
    public void delete(String path) throws Exception {
        zkClient.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
        log.debug("delete, path: {}", path);
    }

    /**
     * 判断路径是否存在
     *
     * @param path 路径
     * @throws Exception
     */
    public boolean checkExist(String path) throws Exception {
        Stat stat = zkClient.checkExists().forPath(path);
        if(stat == null) {
            log.debug("checkExist, path: {} not exist", path);
            return false;
        } else {
            log.debug("checkExist, stat: {}, path: {} already exist", stat, path);
            return true;
        }
    }

    /**
     * 读取数据
     *
     * @param path 路径
     * @throws Exception
     */
    public void read(String path) throws Exception {
        String data = new String(zkClient.getData().forPath(path), "gbk");
        log.debug("read, path: {}, data: {}", path, data);
    }

    /**
     * 获取节点下的子路径
     *
     * @param path 路径
     * @throws Exception
     */
    public void getChildren(String path) throws Exception {
        if (!checkExist(path)) {
            return;
        }
        List<String> paths = zkClient.getChildren().forPath(path);
        for (String p : paths) {
            String childPath = path + "/" + p;
            log.debug("getListChildren, path: {}", childPath);
            //delete(childPath);
        }
    }

    /**
     * 本地文件上传
     *
     * @param zkPath zk路径
     * @param localpath 本地文件路径
     * @throws Exception
     */
    public void upload(String zkPath, String localpath) throws Exception {
        createrOrUpdate(zkPath, "");//创建路径
        byte[] bs = FileUtils.readFileToByteArray(new File(localpath));
        zkClient.setData().forPath(zkPath, bs);
        log.debug("upload, zkPtch: {}, localpath: {}", zkPath, localpath);
    }
}
