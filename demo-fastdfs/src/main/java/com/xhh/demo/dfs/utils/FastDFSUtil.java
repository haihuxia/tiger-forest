package com.xhh.demo.dfs.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * FastDFS 操作类
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/24 下午13:02
 * @since 1.6
 */
@Getter
@Setter
@Slf4j
@Component
public class FastDFSUtil {

    /**
     * DFS配置文件
     */
    private String configFileName = "fastdfs.conf";

    /**
     * 初始化状态
     */
    private boolean status = false;

    /**
     * dfs配置文件
     */
    private File dfsClentFile;


    /**
     * 初始化DFS配置
     */
    @PostConstruct
    public void init() {
        if (!status) {
            try {
                if (null == dfsClentFile || !dfsClentFile.exists()) {
                    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                    InputStream is = classloader.getResourceAsStream(configFileName);
                    log.info("configFileName文件名:{}", configFileName);
                    dfsClentFile = StreamUtil.inputStreamToFile(configFileName, is);
                    log.info("dfsClentFile:{},{}", dfsClentFile.getAbsoluteFile(), dfsClentFile.getName());
                }
                ClientGlobal.init(dfsClentFile.getName());
                log.info("DFS init finished success");
                status = true;
            } catch (Exception e) {
                log.error("static init error:{}", e);
                status = false;
            }
        }
    }

    /**
     * 下载文件
     *
     * @param groupName      下载组，默认为Group1
     * @param remoteFilename DFS上面的文件名
     * @param localFileName  下载到本地的文件名
     * @return int                  文件下载结果
     */
    public int download(String groupName, String remoteFilename, String localFileName) {
        init();
        int result = -1;
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 下载文件
            groupName = StringUtils.isEmpty(groupName) ? "group1" : groupName;
            result = storageClient.download_file(groupName, remoteFilename, localFileName);
            log.info("download result: group_name: {} , remoteFilename: {}, localFileName: {}", groupName, remoteFilename, localFileName);
        } catch (Exception e) {
            log.error("下载文件失败， remoteFilename: {}, localFileName: {}", remoteFilename, localFileName, e);
        } finally {
            closeDFSServer(trackerServer, null);
        }
        return result;
    }

    /**
     * 删除文件
     *
     * @param groupName 组名
     * @param remoteFilename 文件名
     * @return 执行结果
     */
    public int delete(String groupName, String remoteFilename) {
        init();
        int result = -1;
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 删除文件
            groupName = StringUtils.isEmpty(groupName) ? "group1" : groupName;
            result = storageClient.delete_file(groupName, remoteFilename);
        } catch (Exception e) {
            log.error("删除文件失败， remoteFilename: {}, ", remoteFilename, e);
        } finally {
            closeDFSServer(trackerServer, null);
        }
        return result;
    }

    /**
     * 上传文件到FastDFS服务器
     *
     * @param localFileName 文件流
     * @return 远程返回的文件名称和group名称
     */
    public Map<String, String> upload(String localFileName) {
        Map<String, String> dfsMap = new HashMap<String, String>();
        TrackerServer trackerServer = null;
        try {
            // 初始化连接
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);

            byte[] imageBytes = Base64.decodeBase64(localFileName);
            String fileExtName = localFileName.substring(localFileName.lastIndexOf(".") + 1);
            log.debug("fileExtName: {}", fileExtName);
            String[] results = storageClient.upload_file(imageBytes, null, null);        // 上传文件
            if (results == null) {
                log.error("FastDFS文件上传失败,Error Code【{}】", storageClient.getErrorCode());
            } else {
                log.debug("文件上传成功;GROUP_NAME:{},REMOTE_FILE_NAME:{} ", results[0], results[1]);
                dfsMap.put("GROUP_NAME", results[0]);             // 远程返回的文件名称
                dfsMap.put("REMOTE_FILE_NAME", results[1]);       // 文件的groupId
            }
        } catch (Exception e) {
            log.error("FastDFS文件上传异常：{}", e);
        } finally {
            closeDFSServer(trackerServer, null);
        }
        return dfsMap;
    }

    /**
     * 4、关闭队列服务
     *
     * @param trackerServer trackerServer队列服务
     */
    public void closeTrackerServer(TrackerServer trackerServer) {
        // 退出前,一定要将队列服务关闭
        try {
            if (trackerServer != null)
                trackerServer.close();
        } catch (Exception e) {
            log.error("队列服务关闭异常", e);
        } finally {
            status = false;
        }
    }

    /**
     * 关闭数据服务
     *
     * @param storageServer storageServer数据服务
     */
    public void closeStorageServer(StorageServer storageServer) {
        // 退出前,一定要将数据服务关闭
        try {
            if (storageServer != null)
                storageServer.close();
        } catch (Exception e) {
            log.error("数据服务关闭异常", e);
        } finally {
            status = false;
        }
    }

    /**
     * 关闭DFS服务
     *
     * @param trackerServer 队列服务
     * @param storageServer 数据服务
     */
    public void closeDFSServer(TrackerServer trackerServer, StorageServer storageServer) {
        try {
            closeTrackerServer(trackerServer);
            closeStorageServer(storageServer);
        } catch (Exception ioe) {
            log.error("DFS关闭连接异常", ioe.getStackTrace());
        } finally {
            status = false;
        }
    }

}
