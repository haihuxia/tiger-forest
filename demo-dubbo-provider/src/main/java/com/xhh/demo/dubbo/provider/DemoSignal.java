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

    private final Bootstrap bootstrap;

    public DemoSignal(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void handle(Signal signal) {
        log.info("context.stop()--------------");
        log.info("context.stop()--------------");
        try {
            bootstrap.stopAsync();

            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
