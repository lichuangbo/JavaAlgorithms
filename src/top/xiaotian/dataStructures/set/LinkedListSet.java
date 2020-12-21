package top.xiaotian.dataStructures.set;

import top.xiaotian.dataStructures.linkedlist.LinkedList;

/**
 * 基于链表的集合实现
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/20
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    /**
     * 查找O(n),插入O(1)    平均O(n)
     * @param e
     */
    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.addFirst(e);
        }
    }

    /**
     * O(n)
     * @param e
     */
    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    /**
     * O(n)
     * @param e
     * @return
     */
    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
