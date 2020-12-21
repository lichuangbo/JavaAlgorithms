package top.xiaotian.dataStructures.set;

/**
 * @author lichuangbo
 * @version 1.0
 * @created 2020/12/19
 */
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
