package top.xiaotian.algorithms.dp.sub_sequence;

import java.util.Arrays;

/**
 * 674. 最长连续递增序列
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 * <p>
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 */
public class LongestContinueIncreaseSubsequence {
  public int findLengthOfLCIS(int[] nums) {
    int len = nums.length;
    // dp[i]表示以nums[i]结尾的序列的最大连续长度
    int[] dp = new int[len];
    // 初始化：1，每一个数字都可以作为连续递增子序列的最大长度
    // 推演过程中只需要初始化dp[0]就行，为什么需要整个数组都初始化？
    // 因为状态更新是有逻辑的，只有前一个元素小于当前元素才更新，如果不初始化当前元素结尾的最大连续程度就变成0了，进一步影响后续的推演
    Arrays.fill(dp, 1);
    int res = 1;
    for (int i = 1; i < len; i++) {// 处理每个状态
      if (nums[i] > nums[i - 1]) {
        dp[i] = Math.max(dp[i], dp[i - 1] + 1);
        res = Math.max(res, dp[i]);
      }
    }
    return res;
  }

  // 贪心
  public int findLengthOfLCIS2(int[] nums) {
    int res = 1; // 连续子序列最少也是1
    int tmpMax = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i - 1]) { // 连续记录
        tmpMax++;
      } else { // 不连续，count从头开始
        tmpMax = 1;
      }
      res = Math.max(res, tmpMax);
    }
    return res;
  }
}
