package com.xhh.demo.algorithms.struct;

import java.util.Iterator;

/**
 * LIFO 后进先出栈
 *
 * 支持动态调整数据大小
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 22/03/2017 10:58 PM
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];

    private int count = 0;

    private boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    /**
     * 如果存入元素达到数组长度，则将数组扩容两倍
     *
     * @param item 存入元素
     */
    public void push(Item item) {
        if (count == a.length) {
            resize(2 * a.length);
        }
        a[count++] = item;
    }

    /**
     * 如果取出元素后，数据长度变为原来的四分之一，则将数据容量缩减一半
     *
     * @return
     */
    public Item pop() {
        Item item = a[--count];
        a[count] = null;
        if (count > 0 && count == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < count; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = count;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}
