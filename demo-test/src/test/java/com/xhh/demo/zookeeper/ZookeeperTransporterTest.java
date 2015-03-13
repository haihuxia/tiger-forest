package com.xhh.demo.zookeeper;

import com.xhh.demo.BaseSpringTest;
import com.xhh.demo.zookeeper.support.URL;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ZookeeperTransporter 单元测试
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/13 下午11:54
 * @since 1.6
 */
@Slf4j
public class ZookeeperTransporterTest extends BaseSpringTest {

    /** zkClient 封装类 */
    @Autowired(required = false)
    private ZookeeperTransporter zookeeperTransporter;

    @Test
    public void zkTest() throws Exception {
        ZookeeperClient zkClient = zookeeperTransporter.connect(createURL());
        zkClient.create("/test", true);
        log.debug("create node!!!!");
        Thread.sleep(5000);

        zkClient.close();
    }

    /**
     * 创建连接参数
     *
     * @return 连接参数
     */
    public URL createURL() {
        // zkServer host
        String host = "localhost";
        // 连接端口
        int port = 2181;
        return new URL(host, port);
    }
}
