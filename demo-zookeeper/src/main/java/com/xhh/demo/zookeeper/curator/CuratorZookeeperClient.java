package com.xhh.demo.zookeeper.curator;

import com.xhh.demo.zookeeper.ChildListener;
import com.xhh.demo.zookeeper.StateListener;
import com.xhh.demo.zookeeper.support.AbstractZookeeperClient;
import com.xhh.demo.zookeeper.support.URL;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;

import java.util.List;

/**
 * 基于 apache.surator 的 zkClient 接口实现
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/13 下午8:03
 * @since 1.6
 */
public class CuratorZookeeperClient extends AbstractZookeeperClient<CuratorWatcher> {

    /**
     * zkClient
     */
    private final CuratorFramework zkClient;

    /**
     * 构造函数
     *
     * @param url 连接参数
     */
    public CuratorZookeeperClient(URL url) {
        super(url);
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(url.getConnectionString())
                .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
                .connectionTimeoutMs(5000);
        zkClient = builder.build();
        // 增加 zkClient 连接状态监听
        zkClient.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            public void stateChanged(CuratorFramework client, ConnectionState state) {
                if (state == ConnectionState.LOST) {
                    CuratorZookeeperClient.this.stateChanged(StateListener.DISCONNECTED);
                } else if (state == ConnectionState.CONNECTED) {
                    CuratorZookeeperClient.this.stateChanged(StateListener.CONNECTED);
                } else if (state == ConnectionState.RECONNECTED) {
                    CuratorZookeeperClient.this.stateChanged(StateListener.RECONNECTED);
                }
            }
        });
        // zkClient 启动
        zkClient.start();
    }

    /**
     * 创建持久结点
     *
     * @param path 路径
     */
    @Override
    protected void createPersistent(String path) {
        try {
            zkClient.create().forPath(path);
        } catch (KeeperException.NodeExistsException e) {
            // 屏蔽创建已存在结点的异常处理
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 创建临时结点
     *
     * @param path 路径
     */
    @Override
    protected void createEphemeral(String path) {
        try {
            zkClient.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (KeeperException.NodeExistsException e) {
            // 屏蔽创建已存在结点的异常处理
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 删除结点
     *
     * @param path 路径
     */
    @Override
    public void delete(String path) {
        try {
            zkClient.delete().forPath(path);
        } catch (KeeperException.NoNodeException e) {
            // 屏蔽删除时结点不存在的异常处理
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 获得子结点
     *
     * @param path 路径
     * @return 子结点列表
     */
    @Override
    public List<String> getChildren(String path) {
        try {
            return zkClient.getChildren().forPath(path);
        } catch (KeeperException.NoNodeException e) {
            return null;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 判断是否已连接
     *
     * @return 是否已连接
     */
    @Override
    public boolean isConnected() {
        return zkClient.getZookeeperClient().isConnected();
    }

    /**
     * 关闭 zkClient
     */
    @Override
    protected void doClose() {
        CloseableUtils.closeQuietly(zkClient);
    }

    /**
     * 创建自定义监听
     *
     * @param listener 监听
     * @return 监听
     */
    @Override
    protected CuratorWatcher createTargetChildListener(ChildListener listener) {
        return new CuratorWatcherImpl(listener);
    }

    /**
     * 为子结点增加监听
     *
     * @param path 路径
     * @param curatorWatcher 监听
     * @return 子结点列表
     */
    @Override
    protected List<String> addTargetChildListener(String path, CuratorWatcher curatorWatcher) {
        try {
            return zkClient.getChildren().usingWatcher(curatorWatcher).forPath(path);
        } catch (KeeperException.NoNodeException e) {
            return null;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 删除子结点监听
     *
     * @param path 路径
     * @param curatorWatcher 监听
     */
    @Override
    protected void removeTargetChildListener(String path, CuratorWatcher curatorWatcher) {
        ((CuratorWatcherImpl) curatorWatcher).unwatch();
    }

    /**
     * 自定义监听实现
     */
    private class CuratorWatcherImpl implements CuratorWatcher {

        /** 监听 */
        private volatile ChildListener listener;

        /**
         * 构造函数
         */
        public CuratorWatcherImpl(ChildListener listener) {
            this.listener = listener;
        }

        /**
         * 取消监听
         */
        public void unwatch() {
            this.listener = null;
        }

        /**
         * 监听处理
         *
         * @param event 监听事件对象
         * @throws Exception
         */
        @Override
        public void process(WatchedEvent event) throws Exception {
            if (listener != null) {
                listener.childChanged(event.getPath(), zkClient.getChildren().usingWatcher(this).forPath(event.getPath()));
            }
        }
    }
}
