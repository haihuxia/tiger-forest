package com.xhh.demo.hadoop.number;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * 处理数据 Mapper
 *
 * <p>仅传递前5个数字给 Reducer 处理
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-9-3
 * @since 1.6
 */
public class MaxNumberMapper extends MapReduceBase implements Mapper<Object, Text, LongWritable, LongWritable> {

    LongWritable longWritable = new LongWritable();

    @Override
    public void map(Object o, Text text, OutputCollector<LongWritable,
            LongWritable> textIntWritableOutputCollector,
                    Reporter reporter) throws IOException {
        //过滤无效数据
        if(null == text || text.toString().equals("")){
            return;
        }
        String textStr = text.toString();
        //读取 CSV 文件中的数据可能会带一些特殊符号，替换处理
        textStr = textStr.trim().replaceAll(" ", "").replaceAll("\"", "");

        //转换成 LongWritable
        longWritable.set(Long.parseLong(textStr));
        //value 给1，这里可以给空值
        textIntWritableOutputCollector.collect(longWritable, new LongWritable(1));
    }
}
