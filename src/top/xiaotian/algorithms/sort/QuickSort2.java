package top.xiaotian.algorithms.sort;

import top.xiaotian.util.RandomUtil;
import top.xiaotian.util.SwapUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序II-两路快排
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class QuickSort2 {

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

        int pivot = partition(arr, l, r);
        quickSort(arr, l, pivot - 1);
        quickSort(arr, pivot + 1, r);
    }

    // 对数组[l...r]区间进行partition操作
    private int partition(int[] arr, int l, int r) {
        SwapUtil.swap(arr, l, random.nextInt(r) % (r - l + 1) + l);
        int pivot = arr[l];

        // 使arr[l+1...i) <= pivot  arr(j...r] >= pivot      这里都包含了=，是不想让两个区间元素因为相等元素极度不均衡
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i] < pivot) {
                i++;
            }
            while (j >= l + 1 && arr[j] > pivot) {
                j--;
            }

            if (i > j) {
                break;
            }
            SwapUtil.swap(arr, i, j);
            i++; j--;
        }
        SwapUtil.swap(arr, l, j);
        return j;
    }

    public static void main(String[] args) {
        int[] arr = RandomUtil.randomInt(100000, 1, 10);

        new QuickSort2().quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
