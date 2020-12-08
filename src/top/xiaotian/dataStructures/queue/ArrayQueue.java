package top.xiaotian.dataStructures.queue;

import top.xiaotian.dataStructures.array.Array;

/**
 * 顺序队列
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    @Override
    public void enQueue(E e) {
        array.addLast(e);
    }

    /***
     * 出队时间复杂度O(n)
     * 每次出队，都要将后边的数组元素向前移动
     * @return
     */
    @Override
    public E deQueue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front [");
        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if (i != array.getSize() - 1) {
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(4);
        for (int i = 1; i <= 4; i++) {
            arrayQueue.enQueue(i);
            System.out.println(arrayQueue);
        }

        System.out.println(arrayQueue.deQueue());
        System.out.println(arrayQueue);

        arrayQueue.enQueue(10);
        System.out.println(arrayQueue);
    }
}
