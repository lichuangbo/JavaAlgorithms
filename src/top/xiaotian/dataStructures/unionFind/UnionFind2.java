package top.xiaotian.dataStructures.unionFind;

/**
 * 并查集实现2-quick union
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class UnionFind2 implements UF {

    private final int[] parent;// parent[i]表示i这个元素指向的节点

    // size:并查集要处理多少元素
    public UnionFind2(int size) {
        parent = new int[size];
        // 初始状态下，每个元素都属于独立的集合
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
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
        parent[pRoot] = qRoot;
    }
}
