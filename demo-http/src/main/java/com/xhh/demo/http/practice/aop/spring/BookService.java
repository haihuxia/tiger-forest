package com.xhh.demo.http.practice.aop.spring;

/**
 * Created by tiger on 2014/4/9.
 */
public interface BookService {

    void save(String bookName);

    void update(String bookId);

    void list();
}
