package com.xhh.demo.dfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类注释
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/7 上午10:46
 * @since 1.6
 */
@Slf4j
public class FileMoveServerTest extends BaseTest {

    @Autowired
    private FileMoveProvider provider;

    @Autowired
    private FileMoveConsumer consumer;

    @Test
    public void move() {
        log.debug("测试开始。。。。");
        new Thread(provider).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
