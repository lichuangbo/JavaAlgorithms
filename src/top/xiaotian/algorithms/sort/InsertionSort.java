package top.xiaotian.algorithms.sort;

import top.xiaotian.util.RandomUtil;
import top.xiaotian.util.SwapUtil;

import java.util.Arrays;

/**
 * 插入排序
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class InsertionSort {
    /***
     * 时间O(n2)
     * 空间O(1)
     * @param arr
     */
    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    SwapUtil.swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    // 效率高, 在近乎有序的序列中性能更好
    public void insertSort2(int []arr) {
        // 首元素假设为有序的
        for (int i = 1; i < arr.length; i++) {
            int unSort = arr[i];// 待插入元素
            int j; // 保存未排序元素应该插入的位置
            for (j = i; j > 0; j--) {// 有序集合从后向前遍历
                if (unSort < arr[j - 1]) {// 如果未排序元素比有序集合元素小，移动
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = unSort;
        }
    }

    public static void main(String[] args) {
//        int[] arr = RandomUtil.randomInt(10000, 1, 10000);
        int[] arr = RandomUtil.randomNearlySortInt(10000, 79);

        new InsertionSort().insertSort2(arr);
        System.out.println(Arrays.toString(arr));
    }
}
