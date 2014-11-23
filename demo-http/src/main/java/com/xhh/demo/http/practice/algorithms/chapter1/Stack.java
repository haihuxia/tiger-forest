package com.xhh.demo.http.practice.algorithms.chapter1;

import java.util.Iterator;

/**
 * 下压堆栈（链表实现）
 *
 * 优点：可以处理任意类型的数据，所需的空间总是和集合的大小成正比，操作所需的时间总是和集合的大小无关
 *
 * Created by tiger on 14/11/23.
 */
public class Stack<Item> {

    /**
     * 栈顶结点
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
     * 判断栈是否为空
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
     * 向栈顶添加 Item
     * @param item 参数 Item
     */
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    /**
     * 从栈顶消耗 Item
     * @return 消耗的 Item
     */
    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    /**
     * 实现迭代
     * @return 迭代的实现
     */
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    /**
     * 支持后进先出的迭代
     */
    private class StackIterator implements Iterator<Item> {

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

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("苹果");
        stack.push("西瓜");
        stack.push("柚子");
        Iterator<String> i = stack.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
