package top.xiaotian.algorithms.dp.knapsacktotal;

import java.util.Arrays;

/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *
 * 题目数据保证答案符合 32 位整数范围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 *
 * 输入：nums = [9], target = 3
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 *
 *
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 */
public class CombinationSumIV {
    /**
     *          nums[1,2,3] target=4
     *          /       |         \
     *       选1余3   选2余2       选3余1
     *      /  | \
     *    1,2 2,1 3,0
     *    / \
     *   1,1 2,0
     *   /
     *  1,0
     *  暴力法
     *  时间  O(2^n)
     *  空间  O(target/min(nums[i]))  每次递归减少nums[i],递归栈最大深度为target/min(nums[i])
     */
    public int combinationSum4_1(int[] nums, int target) {
        if (target < 0) return 0;
        // 搜索到一种结果
        if (target == 0) return 1;

        int res = 0;
        // 当前层要做的事：基于当前target在数组中依次选择元素，将每个选择对应的结果累加起来
        for (int i = 0; i < nums.length; i++) {
            res += combinationSum4_1(nums, target - nums[i]);
        }
        return res;
    }

    /**
     * 记忆化
     * 时间   O(n*target) target只计算一次，每次要遍历n个元素
     * 空间   O(target)
     */
    public int combinationSum4_2(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return help(nums, target, memo);
    }

    private int help(int[] nums, int target, int[] memo) {
        if (target < 0) return 0;
        if (target == 0) return 1;

        if (memo[target] != -1) {
            return memo[target];
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += help(nums, target - nums[i], memo);
        }
        memo[target] = res;
        return res;
    }

    public int combinationSum4_3(int[] nums, int target) {
        // 定义dp[i]表示组成目标数i的排列数
        // 对于当前目标i，可以选择每一个nums[j],而选择一个nums[j],剩下的组成目标i-nums[j]的排列就是dp[i-nums[j]],由于是求排列问题，需要累加
        // 状态转移方程 dp[i] += dp[i-nums[j]]
        int[] dp = new int[target + 1];
        // 组成0只有一种方式，什么都不选
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
