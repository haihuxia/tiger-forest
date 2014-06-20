package com.xhh.demo.bank.factory;

import com.xhh.demo.bank.common.Response;
import com.xhh.demo.bank.model.BankRequest;
import com.xhh.demo.bank.model.BankResponse;
import com.xhh.demo.bank.service.BaseBankPayService;
import lombok.extern.slf4j.Slf4j;

/**
 * 银行实现工厂入口
 *
 * Created by tiger on 6/18/14.
 */
@Slf4j
public class BankFactory {

    /**
     * 根据银行编码生成对应的实现类
     *
     * @param bankCode
     * @return
     */
    public static BaseBankPayService create(String bankCode) {
        return BankEnumFactory.valueOf(bankCode.toUpperCase()).create();
    }

    /**
     * 模拟根据银行编码查询实现类并调用银行pay方法
     *
     * @param bankRequest
     */
    public void pay(BankRequest bankRequest) {
        BaseBankPayService bankPayService;
        try {
            bankPayService = BankFactory.create(bankRequest.getBankCode());
        } catch (IllegalArgumentException e) {
            log.error("call BankFactory.create error e: {}", e.getMessage());
            log.info("银行编码错误或暂不支持该银行");
            return;
        }
        Response<BankResponse> response = bankPayService.pay(bankRequest);
        log.info("call bankPayService.pay result response: {}", response);
    }

    public static void main(String[] args) {
        BankRequest bankRequest = new BankRequest("psbc");
        BankFactory bankFactory = new BankFactory();
        bankFactory.pay(bankRequest);
    }
}
