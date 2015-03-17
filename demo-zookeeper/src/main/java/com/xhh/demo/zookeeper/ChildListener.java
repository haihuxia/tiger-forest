package com.xhh.demo.zookeeper;

import java.util.List;

/**
 * 子节点监听接口
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/13 下午7:46
 * @since 1.6
 */
public interface ChildListener {

    /**
     * 子节点监听处理
     *
     * @param path 路径
     * @param children 子节点列表
     */
    void childChanged(String path, List<String> children);
}
