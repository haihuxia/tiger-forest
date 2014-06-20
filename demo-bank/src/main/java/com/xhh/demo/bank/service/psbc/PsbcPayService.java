package com.xhh.demo.bank.service.psbc;

import com.xhh.demo.bank.common.Response;
import com.xhh.demo.bank.service.BaseBankPayService;
import com.xhh.demo.bank.model.BankRequest;
import com.xhh.demo.bank.model.BankResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 邮储支付实现类
 *
 * Created by tiger on 6/18/14.
 */
@Slf4j
public class PsbcPayService extends BaseBankPayService {

    @Override
    public Response<BankResponse> pay(BankRequest bankRequest) {
        log.info("call PsbcPayService.pay parameter bankRequest: {}", bankRequest);
        return new Response<BankResponse>(new BankResponse("B"));
    }

    @Override
    public Response<BankResponse> refund(BankRequest bankRequest) {
        log.info("call PsbcPayService.refund parameter bankRequest: {}", bankRequest);
        return new Response<BankResponse>(new BankResponse("B"));
    }

    @Override
    public Response<BankResponse> query(BankRequest bankRequest) {
        log.info("call PsbcPayService.query parameter bankRequest: {}", bankRequest);
        return new Response<BankResponse>(new BankResponse("B"));
    }
}
