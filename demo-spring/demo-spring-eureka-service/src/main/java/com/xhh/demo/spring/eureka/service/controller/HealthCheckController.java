package com.xhh.demo.spring.eureka.service.controller;

import com.xhh.demo.spring.eureka.service.config.ApiUrls;
import com.xhh.demo.spring.eureka.service.util.IPUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 7/26/16 4:13 PM
 */
@Api
@Log4j2
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthCheckController {

    @Autowired
    DiscoveryClient client;

    @RequestMapping("/info")
    public String hello() {
        ServiceInstance localInstance = client.getLocalServiceInstance();
        return "Hello World: "+ localInstance.getServiceId()+":"+localInstance.getHost()+":"+localInstance.getPort();
    }

//    @RequestMapping(value = ApiUrls.HEALTH_CHECK_V1, method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "服务健康检查", tags = {ApiUrls.HEALTH_CHECK_V1})
//    public Object check() {
//        return "{\"status\":\"success\"}";
//    }

    @RequestMapping(value = ApiUrls.APP_LOCAL_IP, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取服务器 IP", tags = {ApiUrls.APP_LOCAL_IP})
    public Object getIp() {
        return "{\"ip\":\"" + IPUtils.getIp() + "\"}";
    }
}
