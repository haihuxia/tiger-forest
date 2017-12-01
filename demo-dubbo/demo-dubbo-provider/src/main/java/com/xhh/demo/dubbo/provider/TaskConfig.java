package com.xhh.demo.dubbo.provider;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 2017/10/31 下午3:35
 */
@Configuration
@EnableAsync
@Component
public class TaskConfig {

    @Async
    public void task() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
