package com.xhh.demo.spring.eureka.service.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * web 配置
 *
 * @author tiger
 * @version 1.0.0 createTime: 16/07/07
 */
@Log4j2
@EnableWebMvc
@Configuration
//@ImportResource({"classpath:spring/spring-extra.xml"})
public class WebContextConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

}