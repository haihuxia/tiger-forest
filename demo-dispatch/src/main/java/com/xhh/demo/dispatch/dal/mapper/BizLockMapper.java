package com.xhh.demo.dispatch.dal.mapper;

import com.xhh.demo.dispatch.dal.models.BizLockDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <ul>
 *     <li>业务锁对象 mapper</li>
 *     <li>Created by tiger on 14-11-18 下午3:48</li>
 * </ul>
 */
public interface BizLockMapper {

    /**
     * 添加锁
     * @param lockName 锁名称
     * @param status 锁状态
     * @param seconds 时间
     * @return 锁对象
     */
    List<BizLockDO> lock(@Param("lockName") String lockName,
                         @Param("status") String status,
                         @Param("seconds") Integer seconds);

    /**
     * 释放锁
     * @param lockName 锁名称
     * @return 返回是否成功
     */
    int releaseLock(@Param("lockName") String lockName);
}
