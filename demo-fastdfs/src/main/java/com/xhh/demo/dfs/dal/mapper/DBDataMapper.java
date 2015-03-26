package com.xhh.demo.dfs.dal.mapper;

import com.xhh.demo.dfs.dal.models.DBModelDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据查询
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/24 上午9:54
 * @since 1.6
 */
public interface DBDataMapper {

    /**
     * 分页查询获取数据总条数
     *
     * @return 数据总条数
     */
    int selectCount();

    /**
     * 分页查询获取数据
     *
     * @param startIndex 开始条数
     * @param endIndex 结束条数
     * @return 数据列表
     */
    List<DBModelDO> selectByPage(@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

}
