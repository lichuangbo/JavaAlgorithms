package top.xiaotian.algorithms.sort;

import top.xiaotian.util.RandomUtil;

import java.util.Arrays;

/**
 * 堆排序
 * 输入数组并建立大顶堆，将堆顶元素与堆底元素交换。完成交换后，堆的长度减 1，已排序元素数量加 1
 * 从堆顶元素开始，从顶到底执行堆化操作。循环执行，即可完成数组排序
 */
public class HeapSort {
    /**
     * 时间   O(nlogn)
     * 空间   O(1)
     * 不稳定排序算法，在交换堆顶元素和堆底元素时，相等元素的相对位置可能发生变化
     */
    public void heapSort(int[] array) {
        int len = array.length;
        // 建立最大堆，从最后一个非叶子节点开始执行下沉操作
        for (int i = (len - 1 - 1) / 2; i >= 0; i--) {
            siftDown(array, i, len - 1);
        }
        for (int i = len - 1; i >= 0; i--) {
            // 将堆顶元素和数组末尾元素交换（即每次都将最大值放入数组末尾索引处）
            swap(array, i, 0);
            // 将交换后的首元素下沉（下沉区间是[0, i-1]，避免影响到交换后的有序值）
            siftDown(array, 0, i - 1);
        }
    }

    /**
     * 下沉操作
     * @param array 元素数组
     * @param i 堆化起始坐标
     * @param n 堆化结尾坐标
     */
    private void siftDown(int[] array, int i, int n) {
        while (true) {
            // 在左右子树中寻找比自己大的值，记录其坐标
            int l = 2 * i + 1, r = 2 * i + 2, maxIndex = i;
            if (l <= n && array[maxIndex] < array[l]) {
                maxIndex = l;
            }
            if (r <= n && array[maxIndex] < array[r]) {
                maxIndex = r;
            }

            // 没有比自己大的，说明满足二叉堆要求，下沉完毕
            if (maxIndex == i) break;

            // 找到比自己大的就交换位置
            swap(array, i, maxIndex);
            // 循环继续，因为下沉完可能仍不满足最大堆性质
            i = maxIndex;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = RandomUtil.randomInt(500, 1, 1000);
        System.out.println("nums=" + Arrays.toString(nums));
        new HeapSort().heapSort(nums);
        System.out.println("heapsort=" + Arrays.toString(nums));
    }
}
