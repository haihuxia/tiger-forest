package com.xhh.demo.zookeeper.support;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * zkClient 连接参数
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/13 下午8:07
 * @since 1.6
 */
@Getter
@Setter
@ToString
public final class URL implements Serializable {

    private static final long serialVersionUID = -174654946049087672L;

    /** 连接 host */
    private final String host;

    /** 端口 */
    private final int port;

    /**
     * 构造函数
     */
    protected URL() {
        host = null;
        port = 0;
    }

    /**
     * 组建连接字符串
     *
     * @return 连接字符串
     */
    public String getConnectionString() {
        return host + ":" + port;
    }
}
