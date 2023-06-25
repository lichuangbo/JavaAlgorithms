package top.xiaotian.algorithms.dp.split;


/**
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 *
 * 1 <= n <= 104
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/19 10:42
 * @Description: 描述: 127 126
 */
public class NumSquares {
    // 递归+记忆化  仿照IntegerBreak实现思路
    public int numSquares(int n) {
        int[] memo = new int[n + 1];
        return dfs(n, memo);
    }

    private int dfs(int n, int[] memo) {
        if (n == 0) {
            return 0;
        }

        // 记忆化操作1
        if (memo[n] != 0) {
            return memo[n];
        }
        int res = Integer.MAX_VALUE;
        /**
         *          4       自顶向下思考，求和为4的完全平方数的最少数量，先采用试探的策略[1, 4, 9... n^2]得到子问题分支树结构
         *        /   \     选1得到3，选2得到0，选3超出范围不考虑。
         *       3     0    此时问题转化为 min(和为3的完全平方数的最少数量, 和为0的完全平方数的最少数量)+1
         *      /           继续画树结构，能够看出 【和为0的完全平方数的最少数量】是递归的出口
         *     2            编码时可以借助循环去处理同一层级的最小值
         *    /
         *   1
         *  /
         * 0
         */
        for (int i = 1; n - i * i >= 0; i++) {
            res = Math.min(res, dfs(n - i * i, memo) + 1);
        }
        // 记忆化操作2
        memo[n] = res;
        return res;
    }

    // 动态规划
    public int numSquares2(int n) {
        // dp[i]表示和为i的完全平方数的最少数目
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {// 求解dp[i]的值
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; i - j * j >= 0; j++) {// 对i进行拆分
                // 状态转移方程：和为i的完全平方数的最少数目=和为i-1*1,i-2*2,...的最小值+1
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
