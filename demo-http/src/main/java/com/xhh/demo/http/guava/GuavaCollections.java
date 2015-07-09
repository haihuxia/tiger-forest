package com.xhh.demo.http.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GuavaCollections
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-6-14 下午2:46
 * @since 1.6
 */
@Service
public class GuavaCollections {

    public void demo() {
        /**
         * 不可变集合
         * ImmutableSet ImmutableSortedMap ImmutableSortedSet
         */
        ImmutableList<String> list = ImmutableList.of("A", "B", "C");
        ImmutableMap<Integer, String> map = ImmutableMap.of(1, "A", 2, "B");

        /**
         * 多值Map
         */
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("A", "110");
        multimap.put("A", "120");

        /**
         * table 地图坐标
         */
        Table<Double, Double, String> g = HashBasedTable.create();
        g.put(31.23, 121.48, "人民广场");

    }

    public void mapFilter() {
        /**
         * 集合工具
         *
         */
        Map<String, Integer> filter = Maps.newHashMap();
        filter.put("A", 20);
        filter.put("B", 23);
        filter.put("C", 24);

        Map<String, Integer> filterMap = Maps.filterValues(filter, new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer input) {
                return input > 21;
            }
        });

        System.out.println(filterMap);
    }

    public void joiner() {
        //定义连接符号
        Joiner joiner = Joiner.on(", ");
        //可以连接多个对象，不局限于String；如果有null， 则跳过
        String str = joiner.skipNulls().join("A", "BC");
        System.out.println("str: " + str);

        Map<String, String> filter = new HashMap<String, String>();
        filter.put("A", "1111");
        filter.put("B", "2222");
        filter.put("C", "3333");
        System.out.println("\n" + Joiner.on("\r\n").withKeyValueSeparator(" to ").join(filter));

        System.out.println("\n");
        String a = "A, B, C";
        for(String s : Splitter.on(", ").split(a)) {
            System.out.println(s);
        }

        System.out.println("\n");
        //指定长度分割
        for(String s : Splitter.fixedLength(2).split(a)) {
            System.out.println(s);
        }
    }

    public void ints() {
        int[] ints = {1, 2, 5, 10, 9, 7};
        System.out.println("\n" + Ints.max(ints));
        List<Integer> list = new ArrayList<Integer>();
        //ints = Ints.toArray(list);
    }

    public static void main(String[] args) {
        GuavaCollections guavaCollections = new GuavaCollections();
        guavaCollections.mapFilter();

        guavaCollections.joiner();

        guavaCollections.ints();
    }

}
