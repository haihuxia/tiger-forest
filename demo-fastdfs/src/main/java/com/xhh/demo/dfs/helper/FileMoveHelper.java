package com.xhh.demo.dfs.helper;

import com.xhh.demo.dfs.dal.mapper.DBDataMapper;
import com.xhh.demo.dfs.dal.models.DBModelDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文件迁移 helper
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/7 上午8:54
 * @since 1.6
 */
@Slf4j
@Component
public class FileMoveHelper {

    @Autowired
    private DBDataMapper dbDataMapper;

    /**
     * 获取数据总条数
     *
     * @return 数据条数
     */
    public int queryCount() {
        return dbDataMapper.selectCount();
//        return 100;
    }

    /**
     * 根据起止条数查询数据
     *
     * @param startIndes 开始索引
     * @param endIndex 结束索引
     * @return 数据列表
     */
    public List<DBModelDO> queryByPage(int startIndes, int endIndex) {
        return dbDataMapper.selectByPage(startIndes, endIndex);
//
//        List<DBModelDO> list = new ArrayList<DBModelDO>();
//        for (int i = 0; i < 10; i++) {
//            DBModelDO model = new DBModelDO();
//            String id = Math.round(Math.random()*8999+1000) + "";
//            log.debug("id: {}", id);
//            model.setId(id);
//            model.setFileName(id);
//            list.add(model);
//        }
//        return list;
    }
}
