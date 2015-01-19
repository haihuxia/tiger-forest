package com.xhh.demo.http.practice.algorithms.chapter1;

import java.util.Iterator;

/**
 * 先进先出队列
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-11-23
 * @since 1.6
 */
public class Queue<Item> {

    /**
     * 最早添加的结点
     */
    private Node first;

    /**
     * 最近添加的结点
     */
    private Node last;

    /**
     * 元素数量
     */
    private int n;

    /**
     * 结点嵌套类
     */
    private class Node {

        /** 当前结点 */
        Item item;

        /** 下一个结点 */
        Node next;
    }

    /**
     * 判读队列是否为空
     * 同样可以使用 n == 0
     * @return 是否为空
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 获取队列长度
     * @return 队列长度
     */
    public int size() {
        return n;
    }

    /**
     * 添加元素到队列尾
     * @param item 参数 Item
     */
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
           first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    /**
     * 从队列头删除元素
     * @return
     */
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        n--;
        return item;
    }

    /**
     * 实现迭代
     * @return 迭代的实现
     */
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    /**
     * 迭代
     */
    private class QueueIterator implements Iterator<Item> {

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
         * 不使用该实现
         */
        @Override
        public void remove() {

        }
    }
}
