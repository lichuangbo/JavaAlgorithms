package top.xiaotian.algorithms.sort;

import top.xiaotian.util.RandomUtil;
import top.xiaotian.util.SwapUtil;

import java.util.Arrays;

public class BubbleSort {

    /**
     * 时间O(n2)  最好O(n)
     * 空间O(1)
     * @param nums
     */
    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {// 外层循环控制循环次数
            boolean flag = false;
            for (int j = 0; j < nums.length - 1; j++) {// 内层循环控制两两交换
                if (nums[j] > nums[j + 1]) {
                    SwapUtil.swap(nums, j, j + 1);
                    flag = true;
                }
            }
            // 如果没有发生过交换，表示已经有序，终止循环
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = RandomUtil.randomInt(10000, 1, 10000);
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
