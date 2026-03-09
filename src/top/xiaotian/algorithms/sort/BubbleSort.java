package top.xiaotian.algorithms.sort;

/**
 * 冒泡排序
 * 连续比较和交换相邻元素，将数组中最大元素交换到最右侧
 */
public class BubbleSort {

    /**
     * 时间 O(n2)
     * 空间 O(1)
     */
    public void bubbleSort(int[] nums) {
        // 未排序区间[0,i]
        for (int i = nums.length - 1; i > 0; i--) {// 外层循环控制循环的趟数，需要循环n-1次，剩余一个元素无序排序
            // 将未排序区间[0,i]中的最大元素交换至该区间的最右端
            for (int j = 0; j < i; j++) {// 内层循环控制比较次数，比较n次
                // 核心”冒泡“逻辑：一趟循环中如果前者大于后者，就两两交换位置
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 时间 O(n2) 最好O(n)
     * 空间 O(1)
     * 稳定排序算法：两元素相等时没有交换
     */
    public void bubbleSort3(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {// 外层循环控制循环的趟数，需要循环n-1次
            // flag变量优化：这次循环是否发生了交换，如果没有说明已经有序直接退出
            boolean flag = false;
            for (int j = 0; j < i; j++) {// 内层循环控制比较次数，比较n次
                // 核心”冒泡“逻辑：一趟循环中如果前者大于后者，就两两交换位置
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }
}
