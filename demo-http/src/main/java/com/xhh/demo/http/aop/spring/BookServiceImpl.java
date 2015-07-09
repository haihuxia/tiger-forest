package com.xhh.demo.http.aop.spring;

import org.springframework.stereotype.Service;

/**
 * BookServiceImpl
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-19
 * @since 1.6
 */
@Service
public class BookServiceImpl {

    public void save(String bookName) {
        System.out.println("执行 save 方法");
    }

    public void update(String bookId) {
        System.out.println("执行 update 方法");
    }

    public void list() {
        System.out.println("执行 list 方法");
    }
}
