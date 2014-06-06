package com.xhh.demo.http.practice.lambdaj;

import ch.lambdaj.Lambda;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 夏海虎 on 2014/4/3.
 *
 * Trove 每个集合乘以一个值
 */
public class ListTest {

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(12);
        ints.add(13);
        ints.add(14);
        ints.add(14);
        int a = Integer.parseInt(Lambda.avg(ints) + "");
        System.out.println("avg: " + a);

        Map<Integer, Integer> map = Lambda.count(ints);
        System.out.println("统计每个元素出现次数：" + map);

        List<Book> books = new ArrayList<Book>();
        books.add(new Book("book1", "3编写高效JAVA代码", "JAVA"));
        books.add(new Book("book3", "2数学之美", "数学"));
        books.add(new Book("book2", "1黑客与画家", "黑客"));
        List<Book> bs = Lambda.sort(books, Lambda.on(Book.class).getBookId());
        System.out.println("\n按bookId排序输出：");
        Book.outPrintLn(bs);

        String bookDesc = Lambda.joinFrom(books).getBookDesc();
        System.out.println("\n串联bookDesc：" + bookDesc);

        List<Book> books1 = Lambda.select(books, new BaseMatcher<Book>() {
            @Override
            public boolean matches(Object item) {
                Book b = (Book)item;
                return b.getBookName().length() > 5;
            }

            @Override
            public void describeTo(Description description) {

            }
        });
        System.out.println("\n过滤书名长度小于5的输出：");
        Book.outPrintLn(books1);

        String maxBookId = Lambda.maxFrom(books).getBookId();
        System.out.println("\n最大bookId为：" + maxBookId);

        List<String> b = Lambda.extract(books, Lambda.on(Book.class).getBookDesc());
        System.out.println("\n数据抽取：" + b);
    }
}
