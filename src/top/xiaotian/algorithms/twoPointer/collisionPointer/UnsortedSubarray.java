package top.xiaotian.algorithms.twoPointer.collisionPointer;

import java.util.Arrays;

/**
 * 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 *
 *
 * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 */
public class UnsortedSubarray {

  /**
   * 排序后对比两个数组
   * 时间：O(nlogn) 空间：O(n)
   */
  public int findUnsortedSubarray(int[] nums) {
    int length = nums.length;
    int[] sort = new int[length];
    System.arraycopy(nums, 0, sort, 0, length);
    Arrays.sort(sort);

    int i = 0, j = length - 1;
    while (i <= j && nums[i] == sort[i]) {
      i++;
    }
    while (i <= j && nums[j] == sort[j]) {
      j--;
    }
    return j - i + 1;
  }

  /**
   * 时间: O(n)
   * 空间：O(1)
   */
  public int findUnsortedSubarray2(int[] nums) {
    int len = nums.length;

    // 左递增区间
    int l = 0; // l 标记从前往后找到第一个出现降序的下标，nums[l] > nums[l + 1]
    while (l < len - 1 && nums[l] <= nums[l + 1]) {
      l++;
    }

    // 若l == len - 1, 说明 nums 为升序序列
    if (l == len - 1) {
      return 0;
    }

    // 右递增区间
    int r = len - 1; // r 标记从后往前找到第一个出现升序的下标，nums[r] < nums[r - 1]
    while (r > 0 && nums[r] >= nums[r - 1]) {
      r--;
    }


    /* 在子区间 [l, r] 中找到最小值 min 和最大值 max*/
    int min = nums[l];
    int max = nums[r];
    for (int i = l; i <= r; i++) {
      min = Math.min(min, nums[i]);
      max = Math.max(max, nums[i]);
    }

    // 因为排完序后，中间乱序的最小值是可能出现在左递增区间中的
    /* 从 l 开始向前查找 min 在 nums 中的最终位置 l */
    while (l > 0 && nums[l - 1] > min) {
      l--;
    }

    /* 从 r 开始向后查找 max 在 nums 中的最终位置 r*/
    while (r < len - 1 && nums[r + 1] < max) {
      r++;
    }

    /* 确定无序子数组的最小值和最大值的最终位置后，[l, r] 中的元素就是原数组 nums 待排序的子数组*/
    return r - l + 1;
  }
}
