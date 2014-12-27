package com.xhh.demo.http.practice.algorithms.chapter1;

/**
 * union-find 算法的实现（加权 quick-union）
 *
 * Created by tiger on 14/11/30.
 */
public class WeightedQuickUnionUF {

    /**
     * 父链接数组
     */
    private int[] id;

    /**
     * 各个根节点所对应的分量的大小
     */
    private int[] sz;

    /**
     * 连通分量的数量
     */
    private int count;

    /**
     * 构造函数
     * @param n 数组长度
     */
    public WeightedQuickUnionUF(int n) {
        count = n;
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * 获得长度
     * @return
     */
    public int count() {
        return count;
    }

    /**
     * 判断 p 和 q 是否存在同一个分量中
     * @param p 触点 p
     * @param q 触点 q
     * @return 是否存在同一个分量中
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * p 所在的分量的标识符
     * @param p 触点 p
     * @return 分量标识符
     */
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    /**
     * 在 p 和 q 之间添加一条连接
     * @param p 触点 p
     * @param q 触点 q
     */
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
}
