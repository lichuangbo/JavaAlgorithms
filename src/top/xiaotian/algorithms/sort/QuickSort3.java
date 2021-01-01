package top.xiaotian.algorithms.sort;

import top.xiaotian.util.RandomUtil;
import top.xiaotian.util.SwapUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序III-三路快排
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class QuickSort3 {

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

    // 对数组[l...r]区间分为<v   ==v   >v三部分
    // 之后递归对<v  >v两部分继续进行三路快排
    private void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        // partition
        SwapUtil.swap(arr, l, random.nextInt(r) % (r - l + 1) + l);
        int pivot = arr[l];

        // arr[l+1...lt] < v     arr[gt...r] > v
        int lt = l, gt = r + 1;
        // arr[lt+1...i) == v
        int i = l + 1;
        while (i < gt) {
            if (arr[i] < pivot) {
                SwapUtil.swap(arr, i, lt + 1);
                lt++; i++;
            } else if (arr[i] > pivot){
                SwapUtil.swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }
        SwapUtil.swap(arr, l, lt);

        quickSort(arr, l, lt - 1);
        quickSort(arr, gt + 1, r);
    }

    public static void main(String[] args) {
        int[] arr = RandomUtil.randomInt(100000, 1, 10);

        new QuickSort3().quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
