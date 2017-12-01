package com.xhh.demo.dubbo.provider;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class DemoAction {

    @Autowired
    private TaskConfig taskConfig;

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    @ResponseBody
    public String doSayHello(@RequestParam String name) {
        log.debug("------------name: {}", name);
        taskConfig.task();
        log.debug("------------result: {}", name);
        return name;
    }

}
