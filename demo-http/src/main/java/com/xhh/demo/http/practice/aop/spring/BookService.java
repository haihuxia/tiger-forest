package com.xhh.demo.http.practice.aop.spring;

/**
 * BookService
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-19
 * @since 1.6
 */
public interface BookService {

    void save(String bookName);

    void update(String bookId);

    void list();
}
