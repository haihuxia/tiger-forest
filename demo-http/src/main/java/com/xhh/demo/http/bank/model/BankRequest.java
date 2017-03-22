package com.xhh.demo.http.bank.model;

import lombok.Data;

/**
 * 银行接口请求参数
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-18
 * @since 1.6
 */
@Data
public class BankRequest {

    private String bankCode;

    public BankRequest(String bankCode) {
        this.bankCode = bankCode;
    }

}
