package com.xhh.demo.dubbo.provider;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * 捕获信号
 *
 * @author tiger
 * @version 1.0.0 createTime: 2015/4/13 21:13
 * @since 1.6
 */
@Slf4j
public class DemoSignal implements SignalHandler {

    // 应用启动对象
    private final Bootstrap bootstrap;

    /**
     * 构造函数
     *
     * @param bootstrap 启动对象
     */
    public DemoSignal(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    /**
     * 捕获信息处理
     *
     * @param signal 信号
     */
    @Override
    public void handle(Signal signal) {
        try {
            // 资源释放
            bootstrap.stopAsync();
            // 应用退出
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
