package com.xhh.demo.http.practice;

import com.xhh.demo.http.BaseTest;
import com.xhh.demo.http.practice.aop.spring.BookService;
import com.xhh.demo.http.practice.aop.spring.BookServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tiger on 2014/4/9.
 */
@Slf4j
public class AopTest extends BaseTest {

    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void learn() {
        bookService.save("数学之美");
    }

    @Getter
    @Setter
    @ToString
    class Fruit {
        String name;
        String color;
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    class Apple extends Fruit {
        String id;
    }

    public static void fruitOut(Fruit fruit) {
        log.debug("fruit: {}", fruit);
    }

    @Test
    public void testClass() {
        Apple apple = new Apple();
        apple.setId("1");
        apple.setName("apple");
        apple.setColor("red");
        fruitOut(apple);
    }

}
