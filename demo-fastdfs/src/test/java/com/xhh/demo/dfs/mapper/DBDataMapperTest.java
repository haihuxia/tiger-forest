package com.xhh.demo.dfs.mapper;

import com.xhh.demo.dfs.BaseTest;
import com.xhh.demo.dfs.dal.mapper.DBDataMapper;
import com.xhh.demo.dfs.dal.models.DBModelDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 数据查询单元测试
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/24 下午2:05
 * @since 1.6
 */
@Slf4j
public class DBDataMapperTest extends BaseTest {

    @Autowired(required = false)
    private DBDataMapper dbDataMapper;

    /**
     * 查询条数
     */
    @Test
    public void selectCount() {
        int count = dbDataMapper.selectCount();
        log.debug("dbDataMapper.selectCount: {}", count);
    }

    /**
     * 查询数据列表
     */
    @Test
    public void selectByPage() {
        List<DBModelDO> list = dbDataMapper.selectByPage(1, 10);
        log.debug("dbDataMapper.selectByPage: {}", list.size());
    }
}
