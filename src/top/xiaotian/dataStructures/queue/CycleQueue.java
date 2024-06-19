package top.xiaotian.dataStructures.queue;


/**
 * 循环队列
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class CycleQueue<E> implements Queue<E>{
    // 基本源数组
    private E[] data;
    // 头索引
    private int front;
    // 尾索引
    private int tail;
    // 源数组容量
    private int size;

    public CycleQueue() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public CycleQueue(int capacity) {
        data = (E[])new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void enQueue(E e) {
        if (isFull()) {
            throw new IllegalArgumentException("队列满了");
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E deQueue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        return data[front];
    }

    public E getRear() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        // tail - 1，获取队尾，因为tail下标是待填充位置；+ data.length是保证计算下边为一个正数（考虑一种情况，队列放满，tail=0，计算出来下标是-1，数组越界了）
        return data[(tail - 1 + data.length) % data.length];
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue: size %d, capacity %d", size, getCapacity()));
        sb.append("\nQueue: front [");
        for (int i = 0; i < size; i++) {
            sb.append(data[(i + front) % data.length]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        CycleQueue<Integer> cycleQueue = new CycleQueue<>(4);
        for (int i = 1; i <= 4; i++) {
            cycleQueue.enQueue(i);
            System.out.println(cycleQueue);
            System.out.println(cycleQueue.getFront() + " " + cycleQueue.getRear());
        }

        System.out.println(cycleQueue.deQueue());
        System.out.println(cycleQueue);

        cycleQueue.enQueue(10);
        System.out.println(cycleQueue);

        System.out.println(cycleQueue.getFront() + " " + cycleQueue.getRear());

        System.out.println((0 - 1) % 4);
    }
}
