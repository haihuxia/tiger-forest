package com.xhh.demo.spring.eureka.client.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 8/19/16 2:55 PM
 */
@FeignClient("eureka-service")
public interface HealthCheckClient {

    @RequestMapping(value = "/api/v1/getIp", method = RequestMethod.GET)
    Object getIp();

}
