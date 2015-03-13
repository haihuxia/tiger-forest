package com.xhh.demo.zookeeper;

/**
 * 状态监听接口
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/13 下午7:54
 * @since 1.6
 */
public interface StateListener {

    /**
     * 端口连接
     */
    int DISCONNECTED = 0;

    /**
     * 已连接
     */
    int CONNECTED = 1;

    /**
     * 重新连接
     */
    int RECONNECTED = 2;

    /**
     * 状态监听处理
     *
     * @param connected 状态标识
     */
    void stateChanged(int connected);
}
