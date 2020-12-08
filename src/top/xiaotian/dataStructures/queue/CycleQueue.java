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
        // 为解决队满和队空判断条件，要浪费一个空间；此处+1是保证容量够用
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void enQueue(E e) {
        // 队列满了,进行扩容
        if ((tail + 1) % data.length == front) {
            resize(2 * getCapacity());
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        @SuppressWarnings("unchecked")
        E[] newData = (E[]) new Object[newCapacity + 1];
        // 循环队列遍历方式1
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
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
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        // 初始化时候多创建了一个，现在要减掉
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue: size %d, capacity %d\n", size, getCapacity()));
        sb.append("Queue: front [");
        // 循环队列遍历方式2
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) {
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        CycleQueue<Integer> cycleQueue = new CycleQueue<>(4);
        for (int i = 1; i <= 5; i++) {
            cycleQueue.enQueue(i);
            System.out.println(cycleQueue);
        }

        System.out.println(cycleQueue.deQueue());
        System.out.println(cycleQueue);

        cycleQueue.enQueue(10);
        System.out.println(cycleQueue);
    }
}
