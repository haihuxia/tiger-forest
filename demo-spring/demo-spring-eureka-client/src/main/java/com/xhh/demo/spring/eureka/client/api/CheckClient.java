package com.xhh.demo.spring.eureka.client.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

/**
 * 模拟 HystrixCommand
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 8/30/16 10:11 AM
 */
@Component
public class CheckClient {

    @HystrixCommand(fallbackMethod = "fallBackCall")
    public String check() {
        return "success";
    }

    public String fallBackCall() {
        return ("FAILED SERVICE CALL! - FALLING BACK [check]");
    }
}
