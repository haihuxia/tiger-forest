package com.xhh.demo.http.practice.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mock 测试准备类
 *
 * @author tiger
 * @version 1.0.0 createTime: 15-1-8 下午7:51
 * @since 1.6
 */
@Component
public class OrderStart {

    @Autowired
    private OrderCreate orderCreate;

    public void start(){
        System.out.println("start...");
        orderCreate.create();
    }
}
