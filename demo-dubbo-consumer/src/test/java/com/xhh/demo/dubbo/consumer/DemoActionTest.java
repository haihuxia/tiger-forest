package com.xhh.demo.dubbo.consumer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 单元测试
 *
 * @author tiger
 * @version 1.0.0 createTime: 2015/4/13 16:49
 * @since 1.6
 */
public class DemoActionTest extends BaseTest {

    @Autowired
    private DemoAction demoAction;

    @Test
    public void doSayHello() {
        demoAction.doSayHello();
    }
}
