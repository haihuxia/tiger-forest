package com.xhh.demo.spring.boot.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerConfig
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/6/24 上午19:06
 */
@Slf4j
@EnableSwagger
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private boolean enabled;

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    @Bean
    public SwaggerSpringMvcPlugin swaggerSpringMvcPlugin() {
        log.info("------- enabled: {}", enabled);
        return new SwaggerSpringMvcPlugin(springSwaggerConfig)
                .includePatterns("/api/v1/.*")
                .apiInfo(new ApiInfo("Demo Boot API", "API version 1.0", "", "", "", ""))
                .enable(enabled);
    }

}