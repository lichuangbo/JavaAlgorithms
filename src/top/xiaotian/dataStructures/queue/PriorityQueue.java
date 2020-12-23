package top.xiaotian.dataStructures.queue;

import top.xiaotian.dataStructures.heap.MaxHeap;

/**
 * 基于堆实现优先队列
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private final MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }


    @Override
    public void enQueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E deQueue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
