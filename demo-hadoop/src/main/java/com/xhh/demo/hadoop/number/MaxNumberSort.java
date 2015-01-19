package com.xhh.demo.hadoop.number;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;

/**
 * 重写 LongWritable.Comparator 的 compare 方法，使数据降序排列
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-9-3
 * @since 1.6
 */
public class MaxNumberSort extends LongWritable.Comparator {

    public int compare(WritableComparable a, WritableComparable b) {
        return -super.compare(a, b);
    }

    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
        return -super.compare(b1, s1, l1, b2, s2, l2);
    }
}
