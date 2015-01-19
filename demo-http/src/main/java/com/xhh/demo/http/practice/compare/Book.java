package com.xhh.demo.http.practice.compare;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.builder.CompareToBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Book
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-3
 * @since 1.6
 */
@ToString
public class Book implements Comparable<Book> {

    @Getter
    @Setter
    private String bookId;

    @Getter
    @Setter
    private String bookName;

    @Getter
    @Setter
    private String bookDesc;

    public Book(String bookId, String bookName, String bookDesc) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookDesc = bookDesc;
    }

    @Override
    public int compareTo(Book book) {
        return new CompareToBuilder().append(bookId, book.bookId).toComparison();
    }

    public static void outPrintLn(List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }

    public static void main(String[] args) {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("book1", "3编写高效JAVA代码", "JAVA"));
        books.add(new Book("book3", "2数学之美", "数学"));
        books.add(new Book("book2", "1黑客与画家", "黑客"));

        System.out.println("不排序输出：");
        outPrintLn(books);

        System.out.println("\n默认排序输出：");
        Collections.sort(books);
        outPrintLn(books);

        System.out.println("\n以书名排序输出：");
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getBookName().compareTo(o2.getBookName());
            }
        });

        outPrintLn(books);

    }
}
