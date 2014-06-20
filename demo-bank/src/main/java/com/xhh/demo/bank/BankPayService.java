package com.xhh.demo.bank;

import com.xhh.demo.bank.common.Response;
import com.xhh.demo.bank.model.BankRequest;
import com.xhh.demo.bank.model.BankResponse;

/**
 * 银行支付基本接口
 * 提供功能：支付、退款、查询
 *
 * Created by tiger on 6/18/14.
 */
public interface BankPayService {

    /**
     * 支付接口
     * @param bankRequest
     * @return
     */
    Response<BankResponse> pay(BankRequest bankRequest);

    /**
     * 退款接口
     * @param bankRequest
     * @return
     */
    Response<BankResponse> refund(BankRequest bankRequest);

    /**
     * 查询接口
     * @param bankRequest
     * @return
     */
    Response<BankResponse> query(BankRequest bankRequest);
}
