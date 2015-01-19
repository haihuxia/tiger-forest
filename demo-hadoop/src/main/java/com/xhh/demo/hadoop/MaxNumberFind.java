package com.xhh.demo.hadoop;

import com.xhh.demo.hadoop.number.MaxNumberCombiner;
import com.xhh.demo.hadoop.number.MaxNumberMapper;
import com.xhh.demo.hadoop.number.MaxNumberReducer;
import com.xhh.demo.hadoop.number.MaxNumberSort;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;

/**
 * 从 HDFS 中的文件中查找最大的5个数字
 *
 * <p>思路：
 * 1.给文件中每个数字添加编号，从1开始，以编号为key，文件中数字为value
 * 2.改变 Hadoop 默认升序排序规则，改为降序
 * 3.输出前5个数字
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-9-3
 * @since 1.6
 */
public class MaxNumberFind {

    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf(MaxNumberFind.class);
        //设置job名称
        conf.setJobName("MaxNumberFind");

        //加载配置文件
        conf.addResource("classpath:/hadoop/core-site.xml");
        conf.addResource("classpath:/hadoop/hdfs-site.xml");
        conf.addResource("classpath:/hadoop/mapred-site.xml");

        conf.setOutputKeyClass(LongWritable.class);
        conf.setOutputValueClass(LongWritable.class);

        //设置排序
        conf.setOutputKeyComparatorClass(MaxNumberSort.class);

        conf.setMapperClass(MaxNumberMapper.class);
        conf.setCombinerClass(MaxNumberCombiner.class);
        conf.setReducerClass(MaxNumberReducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        JobClient.runJob(conf);
        System.exit(0);
    }

}