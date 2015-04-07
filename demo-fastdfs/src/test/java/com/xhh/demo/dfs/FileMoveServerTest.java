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

    @Test
    public void move() {
        provider.moveFile();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
