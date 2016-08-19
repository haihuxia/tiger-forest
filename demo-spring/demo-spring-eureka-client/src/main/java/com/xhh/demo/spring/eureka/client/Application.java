package com.xhh.demo.spring.eureka.client;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 应用启动入口
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 8/16/16 11:26 AM
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF).sources(Application.class).run(args);
    }
}
