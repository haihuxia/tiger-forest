package com.xhh.demo.http.practice;

import com.xhh.demo.http.BaseSpringTest;
import com.xhh.demo.http.aop.spring.BookServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AopTest
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-9
 * @since 1.6
 */
@Slf4j
public class AopTest extends BaseSpringTest {

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
