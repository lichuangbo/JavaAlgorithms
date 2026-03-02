package top.xiaotian.dataStructures.heap;

import top.xiaotian.dataStructures.array.Array;

import java.util.Arrays;
import java.util.Random;

/**
 * 最大堆实现
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class MaxHeap<E extends Comparable<E>> {

    private final Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    // 将任意一个数组整理成堆
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        heapify(arr);
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index 0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /***
     * 完全二叉树结构，稳定
     * 时间 O(logn)
     * @param e 元素e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    // 上浮操作，从节点k开始从底至顶堆化
    private void siftUp(int k) {
        while (true) {
            int p = parent(k);
            // 父亲元素大于当前元素，无需上浮
            if (p < 0 || data.get(k).compareTo(data.get(p)) < 0) {
                break;
            }
            // 父亲元素与当前元素位置进行交换
            data.swap(k, p);
            // 索引移动，循环向上堆化
            k = p;
        }
    }

    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("heap is empty");
        }
        return data.get(0);
    }

    /***
     * O(logn)
     * @return  最大值
     */
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    // 下沉操作，从节点k开始从顶至底堆化
    private void siftDown(int k) {
        while (true) {
            // 从左右节点中找到比索引k代表节点大的索引下标
            int l = leftChild(k), r = rightChild(k), maxIndex = k;
            if (l < size() && data.get(l).compareTo(data.get(maxIndex)) > 0) {
                maxIndex = l;
            }
            if (r < size() && data.get(r).compareTo(data.get(maxIndex)) > 0) {
                maxIndex = r;
            }

            // 当前索引元素比左右字数都大，说明已经符合最大堆性质了，终止
            if (maxIndex == k) {
                break;
            }

            // 不符合，进行交换
            data.swap(k, maxIndex);
            // 循环继续，因为下沉完可能仍不满足最大堆性质
            k = maxIndex;
        }
    }

    // 取出堆中最大的元素，并且替换成元素e
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    /**
     * 堆化
     * 时间 O(n)
     * 倒序遍历堆，依次对每个非叶节点执行“从顶至底堆化”
     * 倒序遍历是为了保证当前节点之下的子树已经是合法的子堆，这样堆化当前节点才是有效的
     */
    private void heapify(E[] arr) {
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
//        MaxHeap<Integer> maxHeap = new MaxHeap<>();
//        Random random = new Random();
//        for (int i = 0; i < n; i++) {
//            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
//        }
//
        Integer[] constructArr = {7, 8, 10, 0, 19, 23, 6, 79};
        n = constructArr.length;
        MaxHeap<Integer> maxHeap = new MaxHeap<>(constructArr);

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("最大堆实现无误");
    }
}
