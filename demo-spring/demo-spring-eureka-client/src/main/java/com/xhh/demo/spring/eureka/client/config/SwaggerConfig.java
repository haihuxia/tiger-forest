package com.xhh.demo.spring.eureka.client.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Swagger 插件配置
 *
 * http://localhost:8080/swagger-ui.html
 *
 * @author tiger
 * @version 1.0.0 createTime: 16/07/07
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private boolean enabled;

    /**
     * 配置 swagger 插件
     *
     * @return 配置对象
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enabled)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex(".*/api/v1/.*"))
                .build();
    }

    /**
     * 插件整体描述信息
     *
     * @return api 描述信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Xhh API")
                .description("Xhh API")
                .version("v1")
                .build();
    }

    public static void main(String[] args) {
        String input = "/example/api/v1/getIp";
        String pathRegex = ".*/api/v1/.*";
        System.out.println(input.matches(pathRegex));

        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match(pathRegex, input));

        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}