package com.xhh.demo.dispatch.manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <ul>
 *     <li>命令状态枚举</li>
 *     <li>Created by tiger on 14-11-18 下午4:09</li>
 * </ul>
 */
@Getter
@AllArgsConstructor
public enum CmdStatusEnum {

    /**
     * 成功
     */
    Success("S", "成功"),

    /**
     * 失败
     */
    Failure("F", "失败"),

    /**
     * 初始化
     */
    Initial("I", "初始化"),

    /**
     * 待处理
     */
    Wait("W", "待处理"),

    /**
     * 处理中
     */
    Processing("P", "处理中");

    /**
     * 编码
     */
    private String code;

    /**
     * 编码说明
     */
    private String desc;

}
