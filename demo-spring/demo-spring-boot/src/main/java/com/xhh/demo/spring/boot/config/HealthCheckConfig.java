package com.xhh.demo.spring.boot.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 8/4/16 10:26 AM
 */
@RestController
public class HealthCheckConfig {

    @RequestMapping("/health")
    public String health() {
        return "check success";
    }
}
