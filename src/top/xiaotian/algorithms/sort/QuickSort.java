package top.xiaotian.algorithms.sort;

import top.xiaotian.util.RandomUtil;
import top.xiaotian.util.SwapUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class QuickSort {

    private Random random;

    /***
     * 时间O(nlogn)
     * 空间0(nlogn)
     * @param arr
     */
    public void quickSort(int[] arr) {
        random = new Random();
        quickSort(arr, 0, arr.length - 1);
    }

    // 对数组[l...r]区间进行快速排序
    private void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p  = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    // 对数组[l...r]区间进行partition操作：使得arr[l...p-1] < arr[p] && arr[p+1...r] > arr[p]
    private int partition(int[] arr, int l, int r) {
        SwapUtil.swap(arr, l, random.nextInt(r) % (r - l + 1) + l);
        int pivot = arr[l];

        // 使arr[l+1...j] < pivot  arr[j+1...i-1] > pivot
        int j = l;// j记录两分区的分界点下标; l是选中的分区点下标(初始状态，[l+1...l]为空区间，[j+1...j]也为空区间)
        for (int i = l + 1; i <= r; i++) {// i是当前遍历的元素下标
            // arr[i] > pivot不操作，属于区间2；< pivot，属于区间1，要进行交换操作（和区间2的第一个元素交换，而后j后移，保证j仍位于分区分界点上）
            if (arr[i] < pivot) {
                SwapUtil.swap(arr, i, j + 1);
                j++;
            }
        }
        // 将pivot放置到中间位置--交换就可以达到目的
        SwapUtil.swap(arr, l, j);
        return j;
    }

    public static void main(String[] args) {
        int[] arr = RandomUtil.randomInt(1000, 1, 1000);

        new QuickSort().quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
