package com.xhh.demo.http.practice;

import com.xhh.demo.http.BaseTest;
import com.xhh.demo.http.practice.aop.spring.BookService;
import com.xhh.demo.http.practice.aop.spring.BookServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tiger on 2014/4/9.
 */
public class AopTest extends BaseTest {

    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void learn() {
        bookService.save("数学之美");
    }

}
