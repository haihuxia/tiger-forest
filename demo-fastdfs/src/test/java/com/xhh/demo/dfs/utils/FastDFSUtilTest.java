package com.xhh.demo.dfs.utils;

import com.xhh.demo.dfs.BaseTest;
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
        Map<String, String> map = fastDFSUtil.uploadByPath("/Users/tiger/ubuntu.png");
        log.debug("fastDFSUtil.uploadStream, map: {}", map);
    }

    @Test
    public void download() {
        String remoteFileName = "M00/04/CF/wKhqC1Ud-HGAeZXRAAF6U-o7fms877.png";
        int count = fastDFSUtil.download(null, remoteFileName, "/Users/tiger/project/logs/aab.png");
        log.debug("fastDFSUtil.download, count: {}", count);
        // 删除
        count = fastDFSUtil.delete(null, remoteFileName);
        log.debug("fastDFSUtil.delete, count: {}", count);
    }
}
