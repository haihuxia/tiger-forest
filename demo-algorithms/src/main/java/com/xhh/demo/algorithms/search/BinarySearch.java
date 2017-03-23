package com.xhh.demo.algorithms.search;

/**
 * 二分查找
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 22/03/2017 10:13 PM
 */
public class BinarySearch {

    /**
     * 递归实现
     *
     * @param key 待查找的值
     * @param a 数组
     * @return 值在数组中的索引
     */
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
    }

    private static int rank(int key, int[] a, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (key < a[mid]) {
            return rank(key, a, start, mid - 1);
        } else if (key > a[mid]) {
            return rank(key, a, mid + 1, end);
        } else {
            return mid;
        }
    }

    /**
     * 循环实现
     *
     * @param key 待查找的值
     * @param a 数组
     * @return 值在数组中的索引
     */
    public static int rank2(int key, int[] a) {
        int start = 0;
        int end = a.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < a[mid]) {
                end = mid - 1;
            } else if (key > a[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6};
        System.out.println(BinarySearch.rank(4, a));
    }
}
