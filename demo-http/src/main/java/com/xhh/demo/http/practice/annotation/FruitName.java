package com.xhh.demo.http.practice.annotation;

import java.lang.annotation.*;

/**
 * FruitName 注解
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-13
 * @since 1.6
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
