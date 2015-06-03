package com.xhh.demo.dfs.utils;

import com.xhh.demo.dfs.pool.FastDFSFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
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
     * DFS 配置文件
     */
    private final static String FASTDFS_CONFI = "fastdfs.conf";

    private ObjectPool<TrackerServer> pool;

    /**
     * 初始化DFS配置
     */
    @PostConstruct
    public void init() throws IOException {
        String fastdfsConfigFilePath =
                ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + FASTDFS_CONFI).getAbsolutePath();
        try {
            ClientGlobal.init(fastdfsConfigFilePath);
            initPool();
            log.info("DFS init finished success");
        } catch (Exception e) {
            log.error("FastDFSUtil init error: {}", e);
        }
    }

    /**
     * 初始化连接池
     */
    public void initPool() {
        PooledObjectFactory<TrackerServer> factory = new FastDFSFactory();
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(15);
        config.setMaxTotal(20);
        config.setMinIdle(6);
        pool = new GenericObjectPool<TrackerServer>(factory, config);
    }

    /**
     * 下载文件
     *
     * @param groupName      下载组，默认为Group1
     * @param remoteFilename DFS上面的文件名
     * @param localFileName  下载到本地的文件名
     * @return int           文件下载结果
     */
    public int download(String groupName, String remoteFilename, String localFileName) {
        int result = -1;
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 下载文件
            groupName = StringUtils.isEmpty(groupName) ? "group1" : groupName;
            result = storageClient.download_file(groupName, remoteFilename, localFileName);
            log.info("download result: group_name: {} , remoteFilename: {}, localFileName: {}",
                    groupName, remoteFilename, localFileName);
        } catch (Exception e) {
            log.error("下载文件失败， remoteFilename: {}, localFileName: {}", remoteFilename, localFileName, e);
        } finally {
            closeTrackerServer(trackerServer);
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
            closeTrackerServer(trackerServer);
        }
        return result;
    }

    /**
     * 上传文件到FastDFS服务器
     *
     * @param localFileStream 文件流
     * @return 远程返回的文件名称和group名称
     */
    public Map<String, String> uploadByStream(String localFileStream) {
        Map<String, String> dfsMap = new HashMap<String, String>();
        TrackerServer trackerServer = null;
        try {
            // 初始化连接
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);

            byte[] fileBytes = Base64.decodeBase64(localFileStream);
            String[] results = storageClient.upload_file(fileBytes, null, null);
            if (results == null) {
                log.error("FastDFS文件上传失败,Error Code【{}】", storageClient.getErrorCode());
            } else {
                log.debug("文件上传成功;GROUP_NAME:{},REMOTE_FILE_NAME:{} ", results[0], results[1]);
                // 远程返回的文件名称
                dfsMap.put("GROUP_NAME", results[0]);
                // 文件的groupId
                dfsMap.put("REMOTE_FILE_NAME", results[1]);
            }
        } catch (Exception e) {
            log.error("FastDFS文件上传异常：{}", e);
        } finally {
            closeTrackerServer(trackerServer);
        }
        return dfsMap;
    }

    /**
     * 上传文件到FastDFS服务器
     *
     * @param localFilePath 文件流
     * @return 远程返回的文件名称和group名称
     */
    public Map<String, String> uploadByPath(String localFilePath) {
        Map<String, String> dfsMap = new HashMap<String, String>();
        TrackerServer trackerServer = null;
        try {
            // 初始化连接
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);

            String[] results = storageClient.upload_file(localFilePath, null, null);
            if (results == null) {
                log.error("FastDFS文件上传失败,Error Code【{}】", storageClient.getErrorCode());
            } else {
                log.debug("文件上传成功;GROUP_NAME:{},REMOTE_FILE_NAME:{} ", results[0], results[1]);
                // 远程返回的文件名称
                dfsMap.put("GROUP_NAME", results[0]);
                // 文件的groupId
                dfsMap.put("REMOTE_FILE_NAME", results[1]);
            }
        } catch (Exception e) {
            log.error("FastDFS文件上传异常：{}", e);
        } finally {
            closeTrackerServer(trackerServer);
        }
        return dfsMap;
    }

    /**
     * 关闭队列服务
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
        }
    }

}
