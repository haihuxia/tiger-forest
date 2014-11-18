package com.xhh.demo.dispatch.dal.mapper;

import com.xhh.demo.dispatch.dal.models.BizCmdDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <ul>
 *     <li>基础命令对象 mapper</li>
 *     <li>Created by tiger on 14-11-18 下午3:48</li>
 * </ul>
 */
public interface BizCmdMapper {

    /**
     * 新增命令
     *
     * @param bizCmdDO
     * @return
     */
    int insert(BizCmdDO bizCmdDO);

    /**
     * 更新命令
     *
     * @param bizCmdDO
     */
    void update(BizCmdDO bizCmdDO);

    /**
     * 查询待处理命令
     *
     * @param bizType 任务类型
     * @param cmdNum  命令数量
     * @return
     */
    List<BizCmdDO> selectToDoCmdList(@Param("bizType") String bizType,
                                     @Param("cmdNum") int cmdNum,
                                     @Param("envTag") String envTag);

    /**
     * 根据业务号，业务类型选取用于分析的数据
     *
     * @param bizId
     * @param bizType
     * @return
     */
    List<BizCmdDO> selectByBizIdAndType(@Param("bizId") Long bizId,
                                        @Param("bizType") String bizType);

    /**
     * 重新激活某个时间点前的所有执行中命令
     *
     * @param date
     */
    int reactiveCommandsBeforeDate(@Param("date") Date date);
}
