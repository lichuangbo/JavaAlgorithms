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
     * 时间O(n2)  最好O(n)
     * 空间O(1)
     * @param arr
     */
    public void insertSort(int[] arr) {
        /**
         * 4 1 3 5
         * 1 4 3 5  swap(1,4)
         * 1 3 4 5  swap(3,4)
         * 1 3 4 5
         * 交换操作较为耗时
         */
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

    /**
     * 时间O(n2)  最好O(n)
     * 空间O(1)
     * @param arr
     */
    // 效率高, 在近乎有序的序列中性能更好
    public void insertSort2(int []arr) {
        // 首元素假设为有序的
        for (int i = 1; i < arr.length; i++) {// 外层for循环往后走
            int unSort = arr[i];// 待插入元素
            int j; // 保存未排序元素应该插入的位置
            for (j = i; j > 0; j--) {// 内层for循环，寻找插入位置
                if (unSort < arr[j - 1]) {// 如果未排序元素比有序集合元素小，数据一个个向后移动
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
