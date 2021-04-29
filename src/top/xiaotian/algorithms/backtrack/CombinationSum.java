package top.xiaotian.algorithms.backtrack;

import java.util.Arrays;

public class CombinationSum {
    /**
     * 377. 组合总和 Ⅳ
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     *
     * 题目数据保证答案符合 32 位整数范围。
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
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] mono = new int[target + 1];
        Arrays.fill(mono, -1);
        return help(nums, target, mono);

        // 动态规划
        // 状态定义：dp[i]表示选取的元素之和为i的方案数
//        int[] dp = new int[target + 1];
//        dp[0] = 1;
//        for (int i = 1; i < target + 1; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (nums[j] <= i) {
//                    dp[i] += dp[i - nums[j]];
//                }
//            }
//        }
//        return dp[target];
    }

    private int help(int[] nums, int target, int[] mono) {
        if (target < 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }

        if (mono[target] != -1) {
            return mono[target];
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += help(nums, target - nums[i], mono);
        }
        mono[target] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 3};
        int res = new CombinationSum().combinationSum4(nums, 35);
        System.out.println(res);
    }
}
