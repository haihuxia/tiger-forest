package com.xhh.demo.spring.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Application
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/6/17 上午12:36
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF).sources(Application.class).web(true).run(args);
    }
}
