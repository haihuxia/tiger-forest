package com.xhh.demo.zookeeper;

import java.util.List;
import com.xhh.demo.zookeeper.support.URL;

/**
 * 基于 apache.surator 的 zkClient 接口
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/13 下午7:33
 * @since 1.6
 */
public interface ZookeeperClient {

    /**
     * 创建节点
     * 1.如果 ephemeral 为 true，创建临时节点，当 zkClient 断开时该节点会被删除
     * 2.如果 ephemeral 为 false，创建持久节点，当 zkClient 断开时该节点不会被删除
     *
     * @param path 路径
     * @param ephemeral 是否为临时节点
     */
    void create(String path, boolean ephemeral);

    /**
     * 删除节点
     *
     * @param path 路径
     */
    void delete(String path);

    /**
     * 获取子节点
     *
     * @param path 路径
     * @return 子节点列表
     */
    List<String> getChildren(String path);

    /**
     * 增加子节点监听
     *
     * @param path 路径
     * @param listener 监听
     * @return 子节点列表
     */
    List<String> addChildListener(String path, ChildListener listener);

    /**
     * 删除子节点监听
     *
     * @param path 路径
     * @param listener 监听
     */
    void removeChildListener(String path, ChildListener listener);

    /**
     * 增加状态节点监听
     *
     * @param listener 监听
     */
    void addStateListener(StateListener listener);

    /**
     * 删除状态节点监听
     *
     * @param listener 监听
     */
    void removeStateListener(StateListener listener);

    /**
     * 判断是否已连接
     *
     * @return 是否已连接
     */
    boolean isConnected();

    /**
     * 关闭 zkClient
     */
    void close();

    /**
     * 获取连接参数
     *
     * @return 连接参数
     */
    URL getUrl();
}
