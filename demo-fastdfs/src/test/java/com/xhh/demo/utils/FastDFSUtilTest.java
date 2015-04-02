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
        Map<String, String> map = fastDFSUtil.upload("/Users/tiger/antx.properties");
        log.debug("fastDFSUtil.uploadStream, map: {}", map);
    }

    @Test
    public void download() {
        String remoteFileName = "M00/04/CF/wKhqC1Uc_bmAL_jhAAAAFG4L_F47611758";
        int count = fastDFSUtil.download(null, remoteFileName, "/Users/tiger/project/logs/aaa.properties");
        log.debug("fastDFSUtil.download, count: {}", count);
        // 删除
        count = fastDFSUtil.delete(null, remoteFileName);
        log.debug("fastDFSUtil.delete, count: {}", count);
    }
}
