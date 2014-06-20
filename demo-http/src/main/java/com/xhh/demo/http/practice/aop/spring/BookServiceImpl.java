package com.xhh.demo.http.practice.aop.spring;

import com.xhh.demo.http.practice.aop.spring.BookService;
import org.springframework.stereotype.Service;

/**
 * Created by tiger on 2014/4/9.
 */
@Service
public class BookServiceImpl {

    //@Override
    public void save(String bookName) {
        System.out.println("执行 save 方法");
    }

    //@Override
    public void update(String bookId) {
        System.out.println("执行 update 方法");
    }

    //@Override
    public void list() {
        System.out.println("执行 list 方法");
    }
}
