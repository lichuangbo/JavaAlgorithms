package top.xiaotian.algorithms.dp.sub_sequence;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/3/17  376
 */
public class LongestIncreaseSubsequence {
  public int lengthOfLIS(int[] nums) {
    int len = nums.length;
    // dp[i]表示以nums[i]结尾的序列的最大长度
    // dp[i]等于以nums[j]结尾的序列的最大值+1，j是比i下标元素小的下标  比如以18结尾的最长递增子序列=以10,9,2,5,3,7结尾的最长递增子序列值的最大值，再加1
    int[] dp = new int[len];
    // 初始化：1，每一个数字都可以作为递增子序列的最大长度
    Arrays.fill(dp, 1);
    int res = 1;
    for (int i = 1; i < len; i++) {// 处理每个状态
      for (int j = 0; j < i; j++) {// 找到比nums[i]小的数字，做状态的更新
        if (nums[j] < nums[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      // 记录最大值
      res = Math.max(res, dp[i]);
    }
    return res;
  }
}