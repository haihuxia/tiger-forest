package com.xhh.demo.http.practice.aop.spring;

/**
 * Created by 夏海虎 on 2014/4/9.
 */
public interface BookService {

    void save(String bookName);

    void update(String bookId);

    void list();
}
