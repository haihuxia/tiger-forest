package com.xhh.demo.utils;

import com.xhh.demo.dfs.BaseTest;
import com.xhh.demo.dfs.utils.FastDFSUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * FastDFSUtil 单元测试
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/24 下午3:02
 * @since 1.6
 */
@Slf4j
public class FastDFSUtilTest extends BaseTest {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Test
    public void upload() {
        Map<String, String> map = fastDFSUtil.uploadStream("/Users/tiger/antx.properties");
        log.debug("fastDFSUtil.uploadStream, map: {}", map);
    }

    @Test
    public void delete() {
        int count = fastDFSUtil.delete(null, "M00/04/BD/wKhqC1URF3qAdwlnAAAAFG4L_F4354.jpg");
        log.debug("fastDFSUtil.delete, count: {}", count);
    }

    @Test
    public void download() {
        int count = fastDFSUtil.downloadFile(null, "M00/04/BD/wKhqC1URF3qAdwlnAAAAFG4L_F4354.jpg",
                "/Users/tiger/project/logs/aaa.properties");
        log.debug("fastDFSUtil.download, count: {}", count);
    }
}
