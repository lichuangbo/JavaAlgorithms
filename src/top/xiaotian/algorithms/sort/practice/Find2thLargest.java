package top.xiaotian.algorithms.sort.practice;

/**
 * 寻找数组中第二大元素
 */
public class Find2thLargest {

  /**
   * 方法1：排序，拿倒数第二大元素
   * 时间复杂度O(nlogn)
   */

  /**
   * 方法2：记录最大值和次大值
   * 时间复杂度O(n)
   */
  public int find2thLargestNumber(int[] nums) {
    int max = nums[0];
    int second = Integer.MIN_VALUE;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > max) {
        second = max;
        max = nums[i];
      } else {// 有可能第一个元素就是最大的（把second也更新一下）
        if (nums[i] > second) {
          second = nums[i];
        }
      }
    }
    return second;
  }

}
