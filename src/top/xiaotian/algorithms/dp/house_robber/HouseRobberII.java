package top.xiaotian.algorithms.dp.house_robber;

/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 *
 * 输入：nums = [1,2,3]
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
//        return Math.max(help(nums, 0, len - 2), help(nums, 1, len - 1));
        return Math.max(help2(nums, 0, len - 2), help2(nums, 1, len - 1));
    }

    /**
     * 时间   O(n)
     * 空间   O(n)
     */
    private int help(int[] nums, int start, int end) {
        int len = end - start + 1;
        if (len == 0) return 0;
        if (len == 1) return nums[start];

        int[] dp = new int[len];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);

        // 求解dp数组
        for (int i = 2; i < len; i++) {
            int numIndex = start + i; // 计算原始数组中的位置
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[numIndex]);
        }
        return dp[len - 1];
    }

    /**
     * 滚动变量
     * 时间   O(n)
     * 空间   O(1)
     */
    private int help2(int[] nums, int start, int end) {
        // 在遍历开始前（即没有任何房屋时），最大收益为0
        // prev2对应dp[i-2]   prev1对应dp[i-1]
        int prev2 = 0, prev1 = 0;
        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}
