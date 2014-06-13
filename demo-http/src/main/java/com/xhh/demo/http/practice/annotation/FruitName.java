package com.xhh.demo.http.practice.annotation;

import java.lang.annotation.*;

/**
 * Created by tiger on 14-6-13.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
