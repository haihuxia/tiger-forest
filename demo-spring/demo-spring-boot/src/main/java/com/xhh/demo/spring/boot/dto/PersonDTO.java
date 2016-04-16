package com.xhh.demo.spring.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Person 对象
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/6/17 上午12:02
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class PersonDTO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;
}
