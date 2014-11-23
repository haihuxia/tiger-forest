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

    private Item[] a = (Item[])new Object[10];

    private int n = 0;

    public boolean isEmpty() {
        return n == 0;
    }

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

    public void push(Item item) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    public Item pop() {
        Item item = a[--n];
        //避免对象游离
        a[n] = null;
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * 支持后进先出的迭代
     */
    private class ReverseArrayIterator implements Iterator<Item> {

        private  int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

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
