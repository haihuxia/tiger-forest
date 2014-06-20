package com.xhh.demo.http.practice.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiger on 2014/4/8.
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
