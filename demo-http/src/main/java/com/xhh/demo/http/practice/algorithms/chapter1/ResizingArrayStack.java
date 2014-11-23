package com.xhh.demo.http.practice.algorithms.chapter1;

import java.util.Iterator;

/**
 * 下压（LIFO）栈
 * 能够动态调整数组大小的实现
 *
 * 缺点：push 和 pop 操作会调整数据的大小，这项操作的耗时和栈大小成正比
 *
 * Created by tiger on 14/11/23.
 */
public class ResizingArrayStack<Item> {

    /**
     * 初始化数组
     */
    private Item[] a = (Item[])new Object[10];

    /**
     * 栈大小
     */
    private int n = 0;

    /**
     * 判断是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 栈的长度
     * @return 长度
     */
    public int size() {
        return n;
    }

    /**
     * 将栈移动到一个大小为 max 的新数组
     * @param max 数据大小
     */
    private void resize(int max) {
        Item[] temp = (Item[])new Object[max];
        System.arraycopy(a, 0, temp, 0, n);
        a = temp;
    }

    /**
     * 添加一个 Item
     * @param item 参数 Item
     */
    public void push(Item item) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    /**
     * 从栈顶消耗一个 Item
     * @return 消耗的 Item
     */
    public Item pop() {
        Item item = a[--n];
        //避免对象游离
        a[n] = null;
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    /**
     * 实现迭代
     * @return 迭代的实现
     */
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * 支持后进先出的迭代
     */
    private class ReverseArrayIterator implements Iterator<Item> {

        /**
         * 长度
         */
        private  int i = n;

        /**
         * 是否存在下一个 Item
         * @return 是否存在
         */
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        /**
         * 获取下一个 Item
         * @return 下一个 Item
         */
        @Override
        public Item next() {
            return a[--i];
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
        ResizingArrayStack<String> resizingArrayStack = new ResizingArrayStack<String>();
        resizingArrayStack.push("苹果");
        resizingArrayStack.push("西瓜");
        resizingArrayStack.push("柚子");
        Iterator<String> i = resizingArrayStack.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
