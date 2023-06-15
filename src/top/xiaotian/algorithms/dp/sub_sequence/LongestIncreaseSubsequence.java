package top.xiaotian.algorithms.dp.sub_sequence;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * 进阶：
 *
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/3/17  376
 */
public class LongestIncreaseSubsequence {
  /**
   * 时间O(n^2)
   */
  public int lengthOfLIS(int[] nums) {
    int len = nums.length;
    // dp[i]表示以nums[i]结尾，能组成的最长严格递增子序列的长度
    int[] dp = new int[len];
    // 初始化：每一个单独的元素都可以作为一个递增子序列，初始化为1
    Arrays.fill(dp, 1);

    int res = 1;
    for (int i = 1; i < len; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {// 状态更新前置条件：后者比它要大，也就是形成了严格递增
          dp[i] = Math.max(dp[i], dp[j] + 1);// 不断取 以下标j元素结尾的子序列能组成的最长递增子序列长度，比较得到最大值，+1就是当前结果
        }
      }

      res = Math.max(res, dp[i]);
    }
    return res;
  }
}
