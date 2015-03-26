package com.xhh.demo.dfs.dal.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 数据库表 DO
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/24 上午10:02
 * @since 1.6
 */
@Getter
@Setter
@ToString
public class DBModelDO {

    /**
     * 主键 id
     */
    private String id;

    /**
     * 文件名
     */
    private String fileName;
}
