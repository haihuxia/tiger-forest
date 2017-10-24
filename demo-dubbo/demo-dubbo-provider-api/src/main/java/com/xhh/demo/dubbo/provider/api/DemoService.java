package com.xhh.demo.dubbo.provider.api;

/**
 * 测试接口
 *
 * @author tiger
 * @version 1.0.0 createTime: 2015/4/13 14:49
 * @since 1.6
 */
public interface DemoService {

    /**
     * 测试方法
     *
     * @param name 姓名
     * @return 问候语
     */
    String sayHello(String name);

    String bye(String name);
}
