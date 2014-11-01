package com.xhh.demo.bank.exception;

import lombok.Getter;

/**
 * 异常封装类
 *
 * Created by tiger on 6/19/14.
 */
public class BankException extends RuntimeException {

    @Getter
    private String errorCode;

    public BankException(String errorCode) {
        this.errorCode = errorCode;
    }

    public BankException(String errorCode, Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
    }

    public BankException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BankException(String errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

}
