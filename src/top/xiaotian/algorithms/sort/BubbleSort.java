package top.xiaotian.algorithms.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

  /**
   * 时间O(n2)
   * 空间O(1)
   * @param nums
   */
  public void bubbleSort(int[] nums) {
    for (int i = 0; i < nums.length; i++) {// 外层循环控制循环的趟数，需要循环n次
      for (int j = 0; j < nums.length - 1; j++) {// 内层循环控制比较次数，比较n次
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
   * 时间O(n2) 最好O(n)
   * 空间O(1)
   * 稳定排序算法：两元素相等时没有交换
   * @param nums
   */
  public void bubbleSort2(int[] nums) {
    for (int i = 0; i < nums.length; i++) {// 外层循环控制循环的趟数，需要循环n次
      // flag变量优化：这次循环是否发生了交换，如果没有说明已经有序直接退出
      boolean flag = false;
      for (int j = 0; j < nums.length - 1; j++) {// 内层循环控制比较次数，比较n次
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
