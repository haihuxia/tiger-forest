package com.xhh.demo.bank.service.icbc;

import com.xhh.demo.bank.common.Response;
import com.xhh.demo.bank.service.BaseBankPayService;
import com.xhh.demo.bank.model.BankRequest;
import com.xhh.demo.bank.model.BankResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 工行支付实现类
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-18
 * @since 1.6
 */
@Slf4j
public class IcbcPayService extends BaseBankPayService {

    @Override
    public Response<BankResponse> pay(BankRequest bankRequest) {
        log.info("call IcbcPayService.pay parameter bankRequest: {}", bankRequest);
        return new Response<BankResponse>(new BankResponse("B"));
    }

    @Override
    public Response<BankResponse> refund(BankRequest bankRequest) {
        log.info("call IcbcPayService.refund parameter bankRequest: {}", bankRequest);
        return new Response<BankResponse>(new BankResponse("B"));
    }

    @Override
    public Response<BankResponse> query(BankRequest bankRequest) {
        log.info("call IcbcPayService.query parameter bankRequest: {}", bankRequest);
        return new Response<BankResponse>(new BankResponse("B"));
    }
}
