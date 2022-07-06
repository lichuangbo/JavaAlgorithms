package top.xiaotian.algorithms.array;

import java.util.Arrays;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 * <p>
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * <p>
 * <p>
 * 限制：
 * <p>
 * 2 <= n <= 100000
 */
public class RepeatNumber {
  // 排序，当相邻的数字相等就找到了重复
  public int findRepeatNumber(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i + 1] - nums[i] == 0) {
        return nums[i];
      }
    }
    return -1;
  }

  // 第一次遇到数字 x 时，将其交换至索引 x 处；而当第二次遇到数字 x 时，一定有 nums[x] = x,此时找到了重复数字
  public int findRepeatNumber2(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      while (nums[i] != i) {
        if (nums[i] == nums[nums[i]]) {
          return nums[i];
        }

        int temp = nums[i];
        nums[i] = nums[temp];
        nums[temp] = temp;
      }
    }
    return -1;
  }
}
