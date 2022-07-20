package top.xiaotian.algorithms.twoPointer.collisionPointer;

import java.util.Arrays;

/**
 * 977. 有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 */
public class SortedSquares {
  /**
   * 时间O(nlogn) 空间O(n)
   */
  public int[] sortedSquares(int[] nums) {
    int[] res = new int[nums.length];
    int k = 0;
    for (int num : nums) {
      res[k++] = num * num;
    }
    Arrays.sort(res);
    return res;
  }

  /**
   * 时间O(n) 空间O(n)
   * 最大值始终在最外层，最小值始终在内层
   */
  public int[] sortedSquares2(int[] nums) {
    int[] res = new int[nums.length];
    int k = nums.length - 1;
    for (int i = 0, j = nums.length - 1; i <= j; ) {
      int left = nums[i] * nums[i];
      int right = nums[j] * nums[j];
      if (left < right) {
        res[k--] = right;
        j--;
      } else {
        res[k--] = left;
        i++;
      }
    }
    return res;
  }
}
