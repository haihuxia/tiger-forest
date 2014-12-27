package com.xhh.demo.http.practice.algorithms.chapter1;

import java.util.Iterator;

/**
 * 背包
 *
 * Created by tiger on 14/11/29.
 */
public class Bag<Item> {

    /**
     * 背包首结点
     */
    private Node first;

    /**
     * 长度
     */
    private int n;

    /**
     * 结点对象
     */
    private class Node {

        /** 当前结点 */
        Item item;

        /** 下一个结点 */
        Node next;
    }

    /**
     * 判断背包是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        //同样可以使用 n == 0 来判断
        return first == null;
    }

    /**
     * 栈长度
     * @return 长度
     */
    public int size() {
        return n;
    }

    /**
     * 向背包首添加 Item
     * @param item 参数 Item
     */
    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    /**
     * 实现迭代
     * @return 迭代的实现
     */
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    /**
     * 迭代
     */
    private class BagIterator implements Iterator<Item> {

        /**
         * 开始结点
         */
        private Node current = first;

        /**
         * 是否存在下一个 Item
         * @return 是否存在
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * 获取下一个 Item
         * @return 下一个 Item
         */
        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        /**
         * 移除
         */
        @Override
        public void remove() {

        }
    }
}
