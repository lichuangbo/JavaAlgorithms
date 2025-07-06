package top.xiaotian.algorithms.dp.split;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 完全背包
     * 转化为：给定了若干个数字，每个数字可以被使用无限次，求凑出目标值  所需要用到的是最少数字个数是多少
     * 物品是不超过n的完全平方数，价值是1（现在是在求计数，每个物品价值都为1），成本是完全平方数本身，背包容量是n
     * dp[i][j] 为考虑前 i个数字，凑出数字总和 j 所需要用到的最少数字数量
     * dp[i][j] = min(dp[i-1][j-k*t] + k)   k表示是取k个完全平方数
     * 时间   O(n^2*sqrt(n))  sqrt(n)是平方数个数，共有 n*sqrt(n)个状态需要转移，每个状态转移最多遍历 n 次
     * 空间   O(n*sqrt(n))
     */
    public int numSquares3(int n) {
        // 预处理出所有可能用到的「完全平方数」
        int maxSquareRoot = (int) Math.sqrt(n);
        int[] squares = new int[maxSquareRoot];
        for (int i = 1; i <= maxSquareRoot; i++) {
            squares[i - 1] = i * i;
        }

        // dp[i][j] 代表考虑前 i 个物品，凑出 j 所使用到的最小元素个数
        int len = squares.length;
        int[][] dp = new int[len][n + 1];

        // 考虑第一个物品来凑满n
        for (int j = 0; j <= n; j++) {
            int k = j / squares[0];
            if (k * squares[0] == j) { // 只有容量为第一个数的整数倍的才能凑出
                dp[0][j] = k;
            } else { // 其余则为无效值
                dp[0][j] = Integer.MAX_VALUE;
            }
        }

        // 处理剩余数的情况
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= n; j++) {
                // 对于不选第 i 个数的情况，使用前i-1个平方数凑成j
                int no = dp[i - 1][j];

                // 对于选第 i 个数的情况,他可以选择k次，从中比较拿到最小值
                int yes = Integer.MAX_VALUE;
                for (int k = 1; k * squares[i] <= j; k++) {
                    // 能够选择 k 个 t 的前提是剩余的数字 j - k * t 也能被凑出
                    if (dp[i - 1][j - k * squares[i]] != Integer.MAX_VALUE) {
                        yes = Math.min(yes, dp[i - 1][j - k * squares[i]] + k);
                    }
                }
                dp[i][j] = Math.min(no, yes);
            }
        }
        return dp[len - 1][n];
    }

    public int numSquares4(int n) {
        // 生成所有不超过 n 的完全平方数
        int maxSquareRoot = (int) Math.sqrt(n);
        int[] squares = new int[maxSquareRoot];
        for (int i = 1; i <= maxSquareRoot; i++) {
            squares[i - 1] = i * i;
        }

        // 初始化 dp 数组，dp[j] 表示组成 j 的最少平方数数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 基础情况

        // 完全背包动态规划
        for (int s : squares) { // 遍历每个平方数（物品）
            for (int j = s; j <= n; j++) { // 遍历容量从 s 到 n
                if (dp[j - s] != Integer.MAX_VALUE) { // 防止溢出
                    dp[j] = Math.min(dp[j], dp[j - s] + 1);
                }
            }
        }

        return dp[n];
    }
}
