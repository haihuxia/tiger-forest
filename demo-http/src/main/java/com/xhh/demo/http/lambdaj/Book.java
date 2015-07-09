package com.xhh.demo.http.lambdaj;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Book
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-3
 * @since 1.6
 */
@ToString
public class Book {

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

    public static void outPrintLn(List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }

}
