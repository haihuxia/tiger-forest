package com.xhh.demo.apache.shiro;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Application
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/6/17 上午12:36
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF).sources(Application.class).web(true).run(args);
    }
}
