package com.xhh.demo.http.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类注释
 * <p/>
 * <p>简单描述类的内容
 *
 * @author tiger
 * @version 1.0.0 createTime: 15-1-8 下午8:17
 * @since 1.6
 */
@Component
public class Order {

    @Autowired
    private OrderBefore orderBefore;

    public void order() {
        System.out.println("order...");
        orderBefore.before();
    }
}
