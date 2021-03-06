package com.xhh.demo.http;

import com.google.common.util.concurrent.AbstractIdleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bootstrap
 *
 * @author tiger
 * @version 1.0.0 createTime: 13-7-23
 * @since 1.6
 */
@Slf4j
public class Bootstrap extends AbstractIdleService {

    private ClassPathXmlApplicationContext context;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.startAsync();
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
        context = new ClassPathXmlApplicationContext(new String[] {"spring/spring-context.xml"});
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
