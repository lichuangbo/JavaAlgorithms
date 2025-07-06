package top.xiaotian.algorithms.dp.house_robber;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobber {
    /**
     *          考虑[1,2,3,1]这组房屋
     *          /    |     |     \
     *        偷1   偷2   偷3     偷1
     *       /      |
     *  只能偷3    只能偷1
     */
    public int rob(int[] nums) {
        // 状态定义：考虑偷取[x...n-1]范围内的房子，但是不一定要偷取x
        // 状态转移方程：f(0)=max(v[0]+f(2), v[1]+f(3), v[2]+f(4) ... v[n-2], v[n-1])   其中v[i]表示房子的价值，f(x)表示偷取[x...n-1]范围内的房子
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums, 0, memo);
    }

    // 递归+记忆化
    // 考虑去抢劫[index, nums.length)范围内的房子
    private int tryRob(int[] nums, int index, int[] memo) {
        if (index >= nums.length) {
            return 0;
        }

        if (memo[index] != -1) {
            return memo[index];
        }
        int res = Integer.MIN_VALUE;
        for (int i = index; i < nums.length; i++) {
            // 抢劫编号为i的这个房屋，并且考虑之后的范围的抢劫
            res = Math.max(res, nums[i] + tryRob(nums, i + 2, memo));
        }
        memo[index] = res;
        return res;
    }

    /**
     * 动态规划：自顶向下考虑
     * 时间O(n^2)
     */
    public int rob2(int[] nums) {
        int len = nums.length;
        // dp[i]为考虑偷取[i...len-1]区间房子的最大收益 (不一定会偷取i的)
        int[] dp = new int[len];
        // 处理最基本的情况，只偷最后一个房子
        dp[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {// 遍历，为dp[i]赋值
            for (int j = i; j < len; j++) {// [i...len-1]区间房子纳入考虑
                dp[i] = Math.max(dp[i], nums[j] + (j + 2 < len ? dp[j + 2] : 0));
            }
        }
        return dp[0];
    }

    /**
     * 动态规划：自低向上考虑
     * 时间O(n)
     */
    public int rob3(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        // dp[i]为考虑偷取[0...i]区间房子的最大收益（不一定会偷取i）
        // 考虑偷取当前的第i个房子，从方法语义来看可以从[0, i-2]拿最大收益   不考虑当前的第i个房子，拿[0. i-1]区间收益
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }
}
