package com.xhh.demo.dubbo.monitor;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.monitor.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 监控
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 2017/10/20 下午5:02
 */
@Slf4j
@Component("monitorService")
public class Monitor implements MonitorService {

    @Override
    public void collect(URL statistics) {
        log.debug("URL: {}", statistics.toFullString());
        log.debug(statistics.getParameter(MonitorService.SUCCESS));

    }

    @Override
    public List<URL> lookup(URL query) {
        return null;
    }

    public static void main(String[] args) {
        URL url = URL.valueOf("count://10.0.193.62:20881/com.xhh.demo.dubbo.provider.api.DemoService/sayHello?" +
                "application=service-provider&concurrent=0&consumer=10.0.193.62&elapsed=0&failure=0&input=0&" +
                "interface=com.xhh.demo.dubbo.provider.api.DemoService&max.concurrent=0&max.elapsed=0&max.input=0&" +
                "max.output=0&method=sayHello&output=0&success=0&timestamp=1508824861504");
        System.out.println(url.getParameter(MonitorService.METHOD));
        System.out.println(url.getParameter(MonitorService.INTERFACE));
        System.out.println(url.getParameter(MonitorService.SUCCESS));
    }
}
