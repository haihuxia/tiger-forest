package com.xhh.demo.bank.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 银行处理响应码
 *
 * 检查校验类响应吗，以CH开头
 *
 * Created by tiger on 6/19/14.
 */
public enum BankRespCode {

    CHECK_BANK_CODE_INVALID("CH0001", "银行编码错误或暂不支持该银行");

    @Getter
    @Setter
    private String respCode;

    @Getter
    @Setter
    private String respMsg;

    BankRespCode(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }
}
