package com.xhh.demo.dispatch.dal.models;

import java.util.Date;

/**
 * <ul>
 *     <li>基础命令对象</li>
 *     <li>Created by tiger on 14-11-18 下午3:22</li>
 * </ul>
 */
public class BizCmdDO extends DataObjectBase {

    private static final long serialVersionUID = 1976631409143108200L;

    /**
     * 业务ID
     */
    private String bizId;
    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 执行的server地址
     */
    private String serverIP;

    /**
     * 最后一次执行失败的原因
     */
    private String failReason;

    /**
     * 环境标签
     */
    private String envTag;

    /**
     * 任务状态 db_column: STATUS
     */
    private String status;
    /**
     * 是否正在处理中 db_column: IS_DOING
     */
    private String isDoing;
    /**
     * 重试次数 db_column: RETRY_TIMES
     */
    private Long retryTimes = 0L;

    /**
     * 最大重试次数，负数表示无限次
     */
    private Long maxRetryTimes = -1L;

    /**
     * 下次执行时间 db_column: NEXT_EXE_TIME
     */
    private Date nextExeTime;

    /**
     * 命令执行起始时间 db_column: ENABLE_START_DATE
     */
    private Date enableStartDate;

    /**
     * 命令执行终止终止时间 db_column: ENABLE_END_DATE
     */
    private Date enableEndDate;
}
