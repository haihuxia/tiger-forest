package com.xhh.demo.dubbo.consumer;

import com.xhh.demo.dubbo.provider.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试处理类
 *
 * @author tiger
 * @version 1.0.0 createTime: 2015/4/13 15:37
 * @since 1.6
 */
@Slf4j
@Controller
public class DemoAction {

    @Autowired
    private DemoService demoService;

    public void doSayHello() {
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            try {
                String hello = demoService.sayHello("world" + i);
                log.debug("[{}] {}", new DateTime().toString("HH:mm:ss"), hello);
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    @ResponseBody
    public String doSayHello(@RequestParam String name) {
        log.debug("------------name: {}", name);
        String result = demoService.sayHello(name);
        log.debug("------------result: {}", result);
        return result;
    }
}
