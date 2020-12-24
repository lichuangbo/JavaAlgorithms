package top.xiaotian.dataStructures.segmentTree;


/**
 * 线段树实现
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class SegmentTree<E> {

    private final E[] tree;
    private final E[] data;
    private final Merger<E> merger;

    @SuppressWarnings("unchecked")
    public SegmentTree(E[] arr, Merger<E> merger) {
        data = (E[]) new Object[arr.length];
        System.arraycopy(arr, 0, data, 0, arr.length);

        this.merger = merger;

        /***
         * 满二叉树，h层一共有2^h-1个节点   其中最后一层节点个数为2^(h-1),所以前h-1层节点个数和大致为最后一层节点个数
         * 如果节点个数n为整数次幂时(2^k)，需要2n空间进行存储
         * 如果不为整数次幂时，假设在最坏情况下(2^k+1，最后一层只有一个节点且位于最右边)，需要4n空间进行存储
         */
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在treeIndex的位置创建表示区间[l, r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if(l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTree = leftChild(treeIndex), rightTree = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTree, l, mid);
        buildSegmentTree(rightTree, mid + 1, r);

        // 和业务相关,此处使用merger接口处理用户传入的处理逻辑
        tree[treeIndex] = this.merger.merge(tree[leftTree], tree[rightTree]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("illegal index!");
        }
        return data[index];
    }

    public int leftChild(int index) {
        return index * 2 + 1;
    }

    public int rightChild(int index) {
        return index * 2 + 2;
    }

    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
            queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("index is illegal");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeIndex为根的线段树其[l, r]区间中中，搜索区间[queryL, queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 搜索区间完全匹配线段树区间，返回
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex), rightChild = rightChild(treeIndex);
        if (queryL >= mid + 1) {// 搜索区间完全位于右子树
            return query(rightChild, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid){// 搜索区间完全位于左子树
            return query(leftChild, l, mid, queryL, queryR);
        }
        // 左右子树都要查询
        E left = query(leftChild, l, mid, queryL, mid);
        E right = query(rightChild, mid + 1, r, mid + 1, queryR);
        return merger.merge(left, right);
    }

    // 将index的位置更新为e
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }

        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e) {
        // 查找到该元素，更新e
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex), rightChild = rightChild(treeIndex);
        if (index >= mid + 1) {// 在右子树中执行更新操作
            set(rightChild, mid + 1, r, index, e);
        } else {// 在右子树中执行更新操作
            set(leftChild, l, mid, index, e);
        }

        // 更新祖辈节点的区间值
        tree[treeIndex] = merger.merge(tree[leftChild], tree[rightChild]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                sb.append(tree[i]);
            } else {
                sb.append("null");
            }

            if (i != tree.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {6, 19, 33, 0, -9, 87};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree);

        System.out.println(segmentTree.query(0, 3));
        System.out.println(segmentTree.query(0, 5));
        System.out.println(segmentTree.query(1, 4));
    }
}
