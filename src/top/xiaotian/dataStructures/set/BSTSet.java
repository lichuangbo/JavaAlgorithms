package top.xiaotian.dataStructures.set;

import top.xiaotian.dataStructures.tree.BST;

/**
 * 基于二叉搜索树的集合实现
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/19
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    /**
     * 时间O(h),其中h是树的高度
     * 在满二叉树的理想情况下，可以推算出h=log(2^n)，但是在最坏情况下树的高度会和节点个数一致（退化为链表）
     * 平均时间复杂度O(logn), 最坏时间复杂度O(n)
     * @param e
     */
    @Override
    public void add(E e) {
        bst.add(e);
    }

    /**
     * 平均0(logn)
     * @param e
     */
    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    /**
     * 平均0(logn)
     * @param e
     */
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
