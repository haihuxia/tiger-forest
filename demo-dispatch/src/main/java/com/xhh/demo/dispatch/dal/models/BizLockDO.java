package com.xhh.demo.dispatch.dal.models;

import lombok.Getter;
import lombok.Setter;

/**
 * <ul>
 *      <li>业务锁对象</li>
 *      <li>Created by tiger on 14-11-18 下午3:39</li>
 * </ul>
 */
@Getter
@Setter
public class BizLockDO extends DataObjectBase {

    private static final long serialVersionUID = 9109912392136508606L;

    /**
     * 锁名称
     */
    private String lockName;

    /**
     * 锁状态
     */
    private String status;

}
