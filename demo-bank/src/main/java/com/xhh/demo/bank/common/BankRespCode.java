package com.xhh.demo.bank.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 银行处理响应码
 *
 * 检查校验类响应吗
 *
 * Created by tiger on 6/19/14.
 */
@Getter
@AllArgsConstructor
public enum BankRespCode {

    CHECK_BANK_CODE_INVALID("CHECK_BANK_CODE_INVALID", "银行编码错误或暂不支持该银行");

    /**
     * 响应代码
     */
    private String respCode;

    /**
     * 响应描述
     */
    private String respMsg;

}
