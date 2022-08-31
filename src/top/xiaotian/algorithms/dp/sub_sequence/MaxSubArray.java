package top.xiaotian.algorithms.dp.sub_sequence;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 53. 最大子数组和
 * <p>
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 * <p>
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4] 输出: 6 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * [-2, 1, ]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5 -100 <= arr[i] <= 100
 */
public class MaxSubArray {

  public int maxSubArray(int[] nums) {
    int len = nums.length;
    // dp[i]表示以i下标元素为结尾的连续子数组的最大和
    int[] dp = new int[len];
    // 初始化：第一个元素就是最大和，即使是负数，因为题目是至少包含一个
    dp[0] = nums[0];
    int res = dp[0];
    for (int i = 1; i < len; i++) {
      dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
      res = Math.max(res, dp[i]);
    }
    return res;
  }

  // 状态压缩
  public int maxSubArray2(int[] nums) {
    int len = nums.length;
    int tmpMax = nums[0];
    int res = nums[0];
    for (int i = 1; i < len; i++) {
      tmpMax = Math.max(tmpMax + nums[i], nums[i]);
      res = Math.max(res, tmpMax);
    }
    return res;
  }

}
