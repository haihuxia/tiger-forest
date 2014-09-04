package com.xhh.demo.hadoop.number;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * Created by tiger on 9/3/14.
 *
 * 处理数据 Mapper
 *
 */
public class MaxNumberMapper extends MapReduceBase implements Mapper<Object, Text, LongWritable, LongWritable> {

    LongWritable longWritable = new LongWritable();

    @Override
    public void map(Object o, Text text, OutputCollector<LongWritable,
            LongWritable> textIntWritableOutputCollector,
                    Reporter reporter) throws IOException {
        //过滤无效数据
        if(null == text || text.equals("")){
            return;
        }
        String textStr = text.toString();
        //读取 CSV 文件中的数据可能会带一些特殊符号，替换处理 TODO 该处理有待优化
        textStr = textStr.trim().replaceAll(" ", "").replaceAll("\"", "");

        //转换成 LongWritable
        longWritable.set(Long.parseLong(textStr));
        //value 给1，这里可以给空值
        textIntWritableOutputCollector.collect(longWritable, new LongWritable(1));
    }
}
