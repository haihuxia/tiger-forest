package com.xhh.demo.http.practice.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * MyException
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-8
 * @since 1.6
 */
public class MyException extends Exception {

    private List<Throwable> causes = new ArrayList<Throwable>();

    public MyException (List<? extends Throwable> _causes) {
        causes.addAll(_causes);
    }

    public List<Throwable> getException() {
        return causes;
    }
}
