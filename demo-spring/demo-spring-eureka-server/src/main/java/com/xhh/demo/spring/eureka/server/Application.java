package com.xhh.demo.spring.eureka.server;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 应用启动入口
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 8/16/16 11:26 AM
 */
@EnableEurekaServer
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF).sources(Application.class).run(args);
    }
}
