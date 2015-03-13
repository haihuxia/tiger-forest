package com.xhh.demo.zookeeper;

import java.util.List;

/**
 * 子结点监听接口
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/13 下午7:46
 * @since 1.6
 */
public interface ChildListener {

    /**
     * 子结点监听处理
     *
     * @param path 路径
     * @param children 子结点列表
     */
    void childChanged(String path, List<String> children);
}
