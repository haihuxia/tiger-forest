package com.xhh.demo.apache.shiro.config;

import com.xhh.demo.apache.shiro.config.realm.SampleRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Apache Shiro 配置
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 9/1/16 10:20 AM
 */
@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(new SampleRealm());
        return defaultWebSecurityManager;
    }
}
