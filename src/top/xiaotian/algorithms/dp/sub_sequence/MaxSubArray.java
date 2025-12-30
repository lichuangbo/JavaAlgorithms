package top.xiaotian.algorithms.dp.sub_sequence;

/**
 * 53. 最大子数组和
 * 剑指 Offer 42. 连续子数组的最大和
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

    /**
     * 动态规划
     * 时间   O(n)
     * 空间   O(n)
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        // dp[i]表示以i下标元素为结尾的连续子数组的最大和
        int[] dp = new int[len];
        // 初始化：第一个元素就是最大和，即使是负数，因为题目是至少包含一个
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            // 如果dp[i-1]为负数，说明之前的状态对当前状态是负贡献，应该采纳nums[i]（num[i]为负数也无所谓，因为必须要包含一个元素）
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 状态压缩
     * 时间   O(n)
     * 空间   O(1)
     */
    public int maxSubArray2(int[] nums) {
        int len = nums.length;
        int tmpMax = nums[0];
        int res = nums[0];
        for (int i = 1; i < len; i++) {
            if (tmpMax < 0) {
                tmpMax = nums[i];
            } else {
                tmpMax = tmpMax + nums[i];
            }
            res = Math.max(res, tmpMax);
        }
        return res;
    }

}
