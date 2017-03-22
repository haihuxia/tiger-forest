package com.xhh.demo.http.bank.exception;

import lombok.Getter;

/**
 * 异常封装类
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-19
 * @since 1.6
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
