package com.xhh.demo.bank.model;

import lombok.Data;

/**
 * 银行接口请求参数
 *
 * Created by tiger on 6/18/14.
 */
@Data
public class BankRequest {

    private String bankCode;

    public BankRequest(String bankCode) {
        this.bankCode = bankCode;
    }

}
