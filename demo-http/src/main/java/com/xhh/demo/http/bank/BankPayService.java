package com.xhh.demo.http.bank;

import com.xhh.demo.http.bank.common.Response;
import com.xhh.demo.http.bank.model.BankRequest;
import com.xhh.demo.http.bank.model.BankResponse;

/**
 * 银行支付基本接口
 *
 * <p>提供功能：支付、退款、查询
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-18
 * @since 1.6
 */
public interface BankPayService {

    /**
     * 支付接口
     * @param bankRequest 请求参数
     * @return 响应
     */
    Response<BankResponse> pay(BankRequest bankRequest);

    /**
     * 退款接口
     * @param bankRequest 请求参数
     * @return 响应
     */
    Response<BankResponse> refund(BankRequest bankRequest);

    /**
     * 查询接口
     * @param bankRequest 请求参数
     * @return 响应
     */
    Response<BankResponse> query(BankRequest bankRequest);
}
