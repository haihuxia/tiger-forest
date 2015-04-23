package com.xhh.demo.dubbo.provider;

import com.google.common.util.concurrent.AbstractIdleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.Signal;

/**
 * Bootstrap
 *
 * @author tiger
 * @version 1.0.0 createTime: 15-4-13
 * @since 1.6
 */
@Slf4j
public class Bootstrap extends AbstractIdleService {

    private ClassPathXmlApplicationContext context;

    /**
     * java -jar 启动应用程序入口
     *
     * <p>对应项目 Bootstrap 类的 main 方法</p>
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        // 启动应用
        bootstrap.startAsync();

        DemoSignal demoSignal = new DemoSignal(bootstrap);
        // 捕获 kill 命令
        Signal.handle(new Signal("TERM"), demoSignal);
        try {
            Object lock = new Object();
            synchronized (lock) {
                while (true) {
                    lock.wait();
                }
            }
        } catch (InterruptedException ex) {
            log.error("ignore interruption");
        }
    }

    /**
     * Start the service.
     */
    @Override
    protected void startUp() throws Exception {
        context = new ClassPathXmlApplicationContext(new String[] {"spring/applicationContext.xml"});
        context.start();
        context.registerShutdownHook();
        log.info("tasks service started successfully");
    }

    /**
     * Stop the service.
     */
    @Override
    protected void shutDown() throws Exception {
        context.stop();
        log.info("tasks stopped successfully");
    }

}
