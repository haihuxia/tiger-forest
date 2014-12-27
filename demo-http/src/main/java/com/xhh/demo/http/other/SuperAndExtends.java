package com.xhh.demo.http.other;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 泛型通配符使用
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-12-15 下午7:04
 * @see java.util.List
 * @since 1.6
 */
@Slf4j
public class SuperAndExtends {

    /**
     * 泛型结构只参与“读”操作则限定上界（extends）
     *
     * @param list 泛型 list
     * @param <E> 限定类型
     */
    public static <E> void read(List<? extends E> list) {
        for (E e : list) {
            log.debug("e: {}", e);
        }
    }

    /**
     * 泛型结构只参与“写”操作则限定下界（super）
     *
     * @param list 泛型 list
     */
    public static void write(List<? super Number> list) {
        list.add(12L);
    }
}
