package com.xhh.demo.hadoop.number;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by tiger on 9/3/14.
 *
 * 处理数据 Reducer
 *
 */
public class MaxNumberCombiner extends MapReduceBase implements Reducer<LongWritable, LongWritable, LongWritable, LongWritable> {

    private LongWritable num = new LongWritable(1);

    @Override
    public void reduce(LongWritable longWritable, Iterator<LongWritable> longWritableIterator,
                       OutputCollector<LongWritable, LongWritable> textIntWritableOutputCollector,
                       Reporter reporter) throws IOException {
        if(num.get() < 6){
            textIntWritableOutputCollector.collect(longWritable, num);
            num = new LongWritable(num.get() + 1);
        }
    }
}
