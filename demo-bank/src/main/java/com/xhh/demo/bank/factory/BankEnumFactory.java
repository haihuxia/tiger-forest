package com.xhh.demo.bank.factory;

import com.xhh.demo.bank.service.BaseBankPayService;
import com.xhh.demo.bank.service.icbc.IcbcPayService;
import com.xhh.demo.bank.service.psbc.PsbcPayService;

/**
 * 银行枚举工厂
 *
 * <p>一个类枚举项最好别超过64个，超过后会有大的性能消耗
 * 性能消耗说明详见《编写高质量代码：改善Java程序的151个建议》书中枚举篇的介绍
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-18
 * @since 1.6
 */
public enum BankEnumFactory {

    ICBC("icbc") {
        BaseBankPayService create() {
            return new IcbcPayService();
        }
    },

    PSBC("psbc") {
        BaseBankPayService create() {
            return new PsbcPayService();
        }
    };

    abstract BaseBankPayService create();

    String bankCode;

    BankEnumFactory(String bankCode) {
        this.bankCode = bankCode;
    }

}
