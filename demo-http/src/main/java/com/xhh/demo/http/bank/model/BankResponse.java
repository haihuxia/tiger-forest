package com.xhh.demo.http.bank.model;

import lombok.Data;

/**
 * 银行接口返回参数
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-18
 * @since 1.6
 */
@Data
public class BankResponse {

    private String tranStatus;

    public BankResponse(String tranStatus) {
        this.tranStatus = tranStatus;
    }
}
