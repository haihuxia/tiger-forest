package com.xhh.demo.dispatch.manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <ul>
 *     <li>任务计划枚举</li>
 *     <li>Created by tiger on 14-11-18 下午4:10</li>
 * </ul>
 */
@Getter
@AllArgsConstructor
public enum ScheduleStatusEnum {

    /**
     * 等待调度
     */
    Waiting("W", "等待调度"),

    /**
     * 处理中
     */
    Processing("P", "处理中"),

    /**
     * 成功
     */
    Succeed("S", "成功"),

    /**
     * 失败
     */
    Failed("F", "失败");

    /**
     * 编码
     */
    private String code;

    /**
     * 编码说明
     */
    private String desc;

}
