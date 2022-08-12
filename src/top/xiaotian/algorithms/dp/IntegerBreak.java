package top.xiaotian.algorithms.dp;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/6 279 91 62 63
 */
public class IntegerBreak {
    // 递归+记忆化
    public int integerBreak(int target) {
        int[] memo = new int[target + 1];
        return help(target, memo);
    }
    // 将n进行分割（至少分割两部分），得到的最大乘积
    private int help(int target, int[] memo) {
        if (target == 1) {
            return 1;
        }

        if (memo[target] != 0) {
            return memo[target];
        }
        int res = Integer.MIN_VALUE;
        // [1...target-1]都是可分割的选项
        for (int i = 1; i <= target - 1; i++) {
            // 可以分割为两部分 i  target-i
            // 第一个参数指 只分割两部分(i, target-i)  第三个参数指继续进行分割， target-i部分继续
            res = Math.max(res, Math.max(i * (target - i), i * help(target - i, memo)));
        }
        memo[target] = res;
        return res;
    }

    // 动态规划
    public int integerBreak2(int target) {
        int[] dp = new int[target + 1];
        dp[1] = 1;
        for (int i = 2; i <= target; i++) {// 求解dp[i]
            for (int j = 1; j <= i - 1; j++) {// 尝试分割 (j, i-j)
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[target];
    }
}
