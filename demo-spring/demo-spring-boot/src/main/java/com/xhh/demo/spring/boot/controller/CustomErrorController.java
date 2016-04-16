package com.xhh.demo.spring.boot.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CustomErrorController
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/6/26 上午7:28
 */
@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "invalid request";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}