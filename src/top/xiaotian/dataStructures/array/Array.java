package top.xiaotian.dataStructures.array;

/**
 * 动态数组实现
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class Array<E> {
    // 基本源数组
    private E[] data;
    // 元素个数
    private int size;

    /***
     * 有参构造
     * @param capacity  初始容量
     */
    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        System.arraycopy(arr, 0, data, 0, arr.length);
        size = arr.length;
    }

    /***
     * 无参构造，默认容量为10
     */
    public Array() {
        this(10);
    }

    /***
     * 获取数组元素个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /***
     * 数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /***
     * 获取数组容量
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /***
     * 向数组指定索引处添加元素
     * @param index 索引位置（索引从0开始）
     * @param e 元素
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add Failed. Require index >= 0 and index < size");
        }
        if (size == data.length) {
//            throw new IllegalArgumentException("Add Failed. Array is Full");
            // 扩容
            resize(2 * data.length);
        }
        // 数据挨个往后移动
        /*
         * 1 2 3 4 5 6  size=6,index=2,e=0
         *     3 3 4 5 6
         * 1 2 0 3 4 5 6
         */
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /***
     * 扩容
     * @param newCapacity 新容量
     */
    private void resize(int newCapacity) {
        @SuppressWarnings("unchecked")
        E[] newData = (E[])new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /***
     * 向数组最后加入一个元素
     * @param e 待插入元素
     */
    public void addLast(E e) {
        // size对应index，不会进入for循环（搬移数据），直接data[index]=e
        add(size, e);
    }

    /***
     * 向数组头部加入一个元素
     * @param e 待插入元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /***
     * 获取索引对应的元素
     * @param index 索引
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get Failed. Require index >= 0 and index < size");
        }
        return data[index];
    }

    /***
     * 获取数组首元素
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /***
     * 获取数组尾元素
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /***
     * 设置索引对应的元素e
     * @param index 索引
     * @param e 元素
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set Failed. Require index >= 0 and index < size");
        }
        data[index] = e;
    }

    /***
     * 数组是否包含某元素
     * @param e 元素
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /***
     * 查找元素e在数组的位置
     * @param e 目标元素
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /***
     * 删除指定索引位置的元素并返回
     * @param index 索引位置
     * @return 该索引对应的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove Failed. Require index >= 0 and index < size");
        }
        // 记录该索引对应元素
        E res = data[index];
        // 从右向左搬移数据
        /*
         * 1 2 3 4 5 6  index=2
         *     4 5 6
         */
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;

        if (size == data.length / 2) {
            resize(data.length / 2);
        }
        return res;
    }

    /***
     * 移除第一个元素
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /***
     * 移除最后一个元素
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /***
     * 从数组中删除元素e
     * @param e 目标元素
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size= %d , capacity= %d\n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Array<Integer> arr = new Array<>(10);

        for (int i = 1; i <= 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(9);
        System.out.println(arr);

        System.out.println(arr.getLast());

        System.out.println(arr.remove(2));
        System.out.println(arr);

        arr.removeElement(10);
        System.out.println(arr);
    }
}
