package top.xiaotian.algorithms.sort;

import top.xiaotian.util.RandomUtil;
import top.xiaotian.util.SwapUtil;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public void heapSort(int[] array) {
        int len = array.length;
        // 建立最大堆，任意索引i的父节点索引是(i-1)/2
        // 从最后一个非叶子节点开始对元素下沉
        for (int i = (len - 1 - 1) / 2; i >= 0; i--) {
            siftDown(array, i, len - 1);
        }
        for (int i = len - 1; i >= 0; i--) {
            // 将堆顶元素和数组末尾元素交换（即每次都将最大值放入数组末尾索引处）
            SwapUtil.swap(array, i, 0);
            // 交换后，堆化i-1到0
            siftDown(array, 0, i - 1);
        }
    }

    /**
     * 下沉操作
     * @param array 元素数组
     * @param stIndex 堆化起始坐标
     * @param enIndex 堆化结尾坐标
     */
    private void siftDown(int[] array, int stIndex, int enIndex) {
        while (true) {
            // 在左右子树中寻找比自己大的值，记录其坐标
            int maxIndex = stIndex;
            if (stIndex * 2 + 1 <= enIndex && array[stIndex] < array[stIndex * 2 + 1]) {
                maxIndex = stIndex * 2 + 1;
            }
            if (stIndex * 2 + 2 <= enIndex && array[maxIndex] < array[stIndex * 2 + 2]) {
                maxIndex = stIndex * 2 + 2;
            }
            // 没有比自己大的，说明满足完全二叉树要求，堆化完毕
            if (maxIndex == stIndex) break;
            // 找到比自己大的就交换位置
            SwapUtil.swap(array, stIndex, maxIndex);
            // 走到较小元素处，接着比较，可能比其他元素还小
            stIndex = maxIndex;
        }
    }

    public static void main(String[] args) {
        int[] arr = RandomUtil.randomInt(10000, 1, 10000);
        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
