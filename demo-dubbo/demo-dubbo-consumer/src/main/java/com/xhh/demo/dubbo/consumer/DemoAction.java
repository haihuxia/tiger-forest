package com.xhh.demo.dubbo.consumer;

import com.xhh.demo.dubbo.provider.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试处理类
 *
 * @author tiger
 * @version 1.0.0 createTime: 2015/4/13 15:37
 * @since 1.6
 */
@Slf4j
@Service
public class DemoAction {

    @Autowired
    private DemoService demoService;

    public void doSayHello() {
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            try {
                String hello = demoService.sayHello("world" + i);
                log.debug("[{}] {}", new DateTime().toString("HH:mm:ss"), hello);
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
