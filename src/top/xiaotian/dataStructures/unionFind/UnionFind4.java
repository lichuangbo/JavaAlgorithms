package top.xiaotian.dataStructures.unionFind;

/**
 * 并查集实现4-对rank进行优化
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class UnionFind4 implements UF {

    private final int[] parent;// parent[i]表示i这个元素指向的节点
    private final int[] rank;// rank[i]表示以i为根的集合中树对应的深度

    // size:并查集要处理多少元素
    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        // 初始状态下，每个元素都属于独立的集合
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
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
        // 元素对应树深度低的的集合合并到深度高的集合上，这样可以使得树的高度尽可能低
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
            // 此操作相当于qRoot增加了一个孩子指向，但是此操作是将pRoot根链接到qRoot根，并不会影响qRoot的高度
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            // 高度相等，链接操作会导致pRoot多一层
            rank[pRoot] += 1;
        }
    }
}
