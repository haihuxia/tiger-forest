package com.xhh.demo.http.practice;

import org.apache.commons.lang.Validate;

/**
 * CommonsLang
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-3-27
 * @since 1.6
 */
public class CommonsLang {

    public static void main(String[] args) {
        Validate.notEmpty("", "parameter is null");
    }
}
