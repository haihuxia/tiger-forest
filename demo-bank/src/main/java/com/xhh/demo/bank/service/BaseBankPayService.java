package com.xhh.demo.bank.service;

import com.xhh.demo.bank.BankPayService;
import com.xhh.demo.bank.common.BankRespCode;
import com.xhh.demo.bank.common.Response;
import com.xhh.demo.bank.factory.BankFactory;
import com.xhh.demo.bank.model.BankRequest;
import com.xhh.demo.bank.model.BankResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 银行支付基本接口抽象类
 * 各银行支付接口实现的统一父类
 *
 * Created by tiger on 6/18/14.
 */
@Slf4j
public class BaseBankPayService implements BankPayService {

    public Response<BankResponse> pay(BankRequest bankRequest) {
        BaseBankPayService bankPayService;
        try {
            bankPayService = BankFactory.create(bankRequest.getBankCode());
        } catch (IllegalArgumentException e) {
            log.error("call BankFactory.create error e: {}", e.getMessage());
            return new Response<BankResponse>(BankRespCode.CHECK_BANK_CODE_INVALID.getRespCode(),
                    BankRespCode.CHECK_BANK_CODE_INVALID.getRespMsg());
        }
        return bankPayService.pay(bankRequest);
    }

    public Response<BankResponse> refund(BankRequest bankRequest) {
        BaseBankPayService bankPayService;
        try {
            bankPayService = BankFactory.create(bankRequest.getBankCode());
        } catch (IllegalArgumentException e) {
            log.error("call BankFactory.create error e: {}", e.getMessage());
            return new Response<BankResponse>(BankRespCode.CHECK_BANK_CODE_INVALID.getRespCode(),
                    BankRespCode.CHECK_BANK_CODE_INVALID.getRespMsg());
        }
        return bankPayService.refund(bankRequest);
    }

    public Response<BankResponse> query(BankRequest bankRequest) {
        BaseBankPayService bankPayService;
        try {
            bankPayService = BankFactory.create(bankRequest.getBankCode());
        } catch (IllegalArgumentException e) {
            log.error("call BankFactory.create error e: {}", e.getMessage());
            return new Response<BankResponse>(BankRespCode.CHECK_BANK_CODE_INVALID.getRespCode(),
                    BankRespCode.CHECK_BANK_CODE_INVALID.getRespMsg());
        }
        return bankPayService.query(bankRequest);
    }
}
