package com.xhh.demo.bank.model;

import lombok.Data;

/**
 * 银行接口返回参数
 *
 * Created by tiger on 6/18/14.
 */
@Data
public class BankResponse {

    private String tranStatus;

    public BankResponse(String tranStatus) {
        this.tranStatus = tranStatus;
    }
}
