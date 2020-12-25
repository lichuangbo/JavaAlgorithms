package top.xiaotian.dataStructures.unionFind;

/**
 * 并查集实现3-对size进行优化
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class UnionFind3 implements UF {

    private final int[] parent;// parent[i]表示i这个元素指向的节点
    private final int[] sz;// sz[i]表示以i为根的集合中元素个数

    // size:并查集要处理多少元素
    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        // 初始状态下，每个元素都属于独立的集合
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找元素p所属于的根节点集合
    private int find(int p) {
        if(p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        // 只有节点指向自己才算找到根
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    /***
     * 时间O(h)   h为树的高度
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /***
     * 时间O(h)   h为树的高度
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p), qRoot = find(q);
        // 两元素本就同属一个集合，不做处理
        if (pRoot == qRoot) {
            return;
        }

        // p union q, 就是p的根指向q的根
        // 元素个数少的集合合并到元素个数多的集合上，这样可以使得树的高度尽可能低
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
