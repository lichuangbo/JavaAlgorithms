package top.xiaotian.dataStructures.queue;

/**
 * 顺序队列
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public interface Queue<E> {
    void enQueue(E e);
    E deQueue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
