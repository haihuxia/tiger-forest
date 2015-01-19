package com.xhh.demo.hadoop.number;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * 处理数据 Reducer
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-9-3
 * @since 1.6
 */
public class MaxNumberReducer extends MapReduceBase implements Reducer<LongWritable, LongWritable, LongWritable, LongWritable> {

    private LongWritable num = new LongWritable(1);

    @Override
    public void reduce(LongWritable longWritable, Iterator<LongWritable> longWritableIterator,
                       OutputCollector<LongWritable, LongWritable> textIntWritableOutputCollector,
                       Reporter reporter) throws IOException {
        while(longWritableIterator.hasNext()){
            //给数据编号，编号作为 key，并且取前5个数据
            if(num.get() < 6){
                textIntWritableOutputCollector.collect(num, longWritable);
                num = new LongWritable(num.get() + 1);
            }
            //下面这行不能丢，否则死循环
            longWritableIterator.next().get();
        }
    }
}
