package com.xhh.demo.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 启动入口
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 2017/4/17 下午3:03
 */
@ImportResource({"classpath:spring/applicationContext.xml"})
@SpringBootApplication(scanBasePackages = {"com.xhh.demo.dubbo"})
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
