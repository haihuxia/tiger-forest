package com.xhh.demo.http.bank.service.icbc;

import com.xhh.demo.http.bank.common.Response;
import com.xhh.demo.http.bank.model.BankRequest;
import com.xhh.demo.http.bank.model.BankResponse;
import com.xhh.demo.http.bank.service.BaseBankPayService;
import lombok.extern.log4j.Log4j2;

/**
 * 工行支付实现类
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-18
 * @since 1.6
 */
@Log4j2
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
