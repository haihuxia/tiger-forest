package com.xhh.demo.spring.eureka.client.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取服务器 IP
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 8/1/16 3:06 PM
 */
public class IPUtils {

    /**
     * 获取 IP
     *
     * @return ip
     */
    public static String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
