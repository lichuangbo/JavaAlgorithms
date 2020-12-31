package top.xiaotian.algorithms.sort;

import top.xiaotian.util.RandomUtil;
import top.xiaotian.util.SwapUtil;

import java.util.Arrays;

/**
 * 选择排序
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class SelectionSort {
    /***
     * 时间O(n2)
     * 空间O(1)
     * @param arr
     */
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            SwapUtil.swap(arr, i, minIndex);
        }
    }

    public static void main(String[] args) {
        int[] arr = RandomUtil.randomInt(10000, 1, 10000);

        SelectionSort sort = new SelectionSort();
        sort.selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
