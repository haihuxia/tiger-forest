package com.xhh.demo.http.practice.algorithms.chapter1;

/**
 * quick-find
 *
 * <P>quick-find 算法的运行时间对于最终只能得到少数连通分量的一般应用是平方级别的
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-11-23
 * @since 1.6
 */
public class QuickFindUF {

    /**
     * 分量 id (以触点作为索引)
     */
    private int[] id;

    /**
     * 分量数量
     */
    private int count;

    /**
     * 构造函数
     * @param n 数组长度
     */
    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
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
        return id[p];
    }

    /**
     * 在 p 和 q 之间添加一条连接
     * @param p 触点 p
     * @param q 触点 q
     */
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        // 如果存在于同一个分量中不需要采取任何处理
        if (pID == qID) {
            return;
        }
        // 将 p 的分量重命名为 q 的名称
        for (int i = 0; i < id.length; i ++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count--;
    }
}
