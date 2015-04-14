package com.xhh.demo.dfs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * web 请求
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/4/7 下午3:53
 * @since 1.6
 */
@Controller
@Slf4j
public class FileMoveController {

    /** 文件迁移 */
    @Autowired
    private FileMoveProvider fileMoveProvider;

    /**
     * 文件迁移请求处理
     *
     * @return 跳转页面
     */
    @RequestMapping(value={"/move.do"})
    public String move() {
        log.debug("start move...");
        fileMoveProvider.moveFile();
        return "index.jsp";
    }
}
