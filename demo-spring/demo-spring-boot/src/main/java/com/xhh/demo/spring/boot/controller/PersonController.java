package com.xhh.demo.spring.boot.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.xhh.demo.dubbo.provider.api.DemoService;
import com.xhh.demo.spring.boot.config.ApiUrls;
import com.xhh.demo.spring.boot.dto.PersonDTO;
import com.xhh.demo.spring.boot.hystrix.PsersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Person Controller
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/6/17 上午12:32
 */
@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired(required = false)
    private DemoService demoService;

    @Value("${logger.error.appender}")
    private String a;

    /**
     * 返回用户信息
     *
     * @return Person 对象
     */
    @RequestMapping(value = ApiUrls.PERSONS_SHOW_V1, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "显示用户信息")
    public PersonDTO showPerson() {
        log.info("-------------: {}", a);
        ThreadContext.put("LOGID", UUID.randomUUID().toString());
        log.info("+++++++++++++: {}", ThreadContext.get("LOGID"));
        return new PersonDTO("tiger", "tiger@gmail.com");
    }

    /**
     * 调用 sayHello
     *
     * @param name 名字
     * @return 调用结果
     */
    @RequestMapping(value = ApiUrls.PERSONS_SHOW_V2, method = {RequestMethod.GET, RequestMethod.HEAD})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "调用 doPersionSayHello")
    public PersonDTO doPersionSayHello(@RequestParam String name) {
        log.info("------------name: {}", name);
        String result = PsersonService.sayHello(name);
        log.info("------------result: {}", result);
        return new PersonDTO("tiger", result);
    }

    /**
     * 调用 sayHello
     *
     * @param name 名字
     * @return 调用结果
     */
    @RequestMapping(value = "/say", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "调用 doSayHello")
    public String doSayHello(@RequestParam String name) {
        log.info("------------name: {}", name);
        String result = demoService.sayHello(name);
        log.info("------------result: {}", result);
        return result;
    }
}
