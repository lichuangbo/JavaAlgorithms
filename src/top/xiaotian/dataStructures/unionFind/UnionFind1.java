package top.xiaotian.dataStructures.unionFind;

/**
 * 并查集实现1-quick find
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class UnionFind1 implements UF {

    private final int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        // 初始状态下，每个元素都属于独立的集合
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    // 查找元素p所属于的集合编号
    private int find(int p) {
        if(p < 0 || p >= id.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return id[p];
    }

    /***
     * 时间O(1)
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /***
     * 时间O(n)
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pId = find(p), qId = find(q);
        // 两元素本就同属一个集合，不做处理
        if (pId == qId) {
            return;
        }
        // p,q关联，p和q所在的集合都进行关联
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }
}
