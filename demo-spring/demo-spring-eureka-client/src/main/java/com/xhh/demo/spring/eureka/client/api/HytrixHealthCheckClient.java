package com.xhh.demo.spring.eureka.client.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 模拟 HystrixCommand
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 8/19/16 2:35 PM
 */
@Component
public class HytrixHealthCheckClient {

    @Autowired
    private HealthCheckClient healthCheckClient;

    @HystrixCommand(groupKey = "helloGroup", fallbackMethod = "fallBackCall")
    public Object getIp() {
        return healthCheckClient.getIp();
    }

    public String fallBackCall() {
        String fallback = ("FAILED SERVICE CALL! - FALLING BACK");
        return fallback;
    }

}
