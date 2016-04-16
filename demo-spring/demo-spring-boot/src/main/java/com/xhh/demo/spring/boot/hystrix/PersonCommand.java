package com.xhh.demo.spring.boot.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * PersonCommand
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/7/13 下午4:20
 */
public class PersonCommand extends HystrixCommand<String> {

    private final String name;

    public PersonCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("PersonCommand"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    protected String getFallback() {
        return String.format("[FallBack]Hello %s!", name);
    }

    @Override
    protected String run() throws Exception {
        //Thread.sleep(600);
        return String.format("Hello %s!", name);
    }
}
