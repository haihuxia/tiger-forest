package com.xhh.demo.spring.eureka.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 8/30/16 10:17 AM
 */
@Service
public class CheckService {

    @Autowired
    private CheckClient checkClient;

    public String check1() {
        return checkClient.check();
    }
}
