package com.xhh.demo.dubbo.provider.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.xhh.demo.dubbo.provider.api.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

/**
 * 测试接口实现类
 *
 * @author tiger
 * @version 1.0.0 createTime: 2015/4/13 14:50
 * @since 1.6
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    /**
     * 测试方法
     *
     * @param name 姓名
     * @return 问候语
     */
    @Override
    public String sayHello(String name) {
        log.debug("[{}] Hello {}, request from consumer: {}", new DateTime().toString("HH:mm:ss"), name,
                RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response form provider: " + RpcContext.getContext().getLocalAddress();
    }

    @Override
    public String bye(String name) {
        return "bye " + name;
    }
}
