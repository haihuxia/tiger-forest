package com.xhh.demo.dfs.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 数据库表 DO
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/24 下午14:02
 * @since 1.6
 */
@Slf4j
public class StreamUtil {

    /**
     * 关闭流
     *
     * @param os
     */
	public static void closeOutputStream(OutputStream os) {
		// 退出前,一定要将文件处理读写器关闭
		try {
			if (os != null)
				os.close();
		} catch (Exception e) {
            log.error("文件流关闭异常", e);
		}
	}

    /**
     * 将[fastDFS.conf]文件内容写入到新生成的[client.conf]文件中
     *
     * @param fileName 新生成的配置文件名称
     * @param is 原配置文件字节流
     * @return File 新生成的配置
     */
    public static File inputStreamToFile(String fileName, InputStream is){
        File file=new File(fileName);
        OutputStream os = null;
        int len ;
        try{
            os=new FileOutputStream(file);
            byte buffer[]=new byte[4*1024];
            while((len = is.read(buffer)) != -1){
                os.write(buffer,0,len);
            }
            os.flush();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            closeOutputStream(os);
        }
        return file;
    }
}