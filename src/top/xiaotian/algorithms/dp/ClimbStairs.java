package top.xiaotian.algorithms.dp;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/6 120 64
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        // 递归(从上向下思考)：爬n阶台阶有两种途径 (1)-先爬n-1阶台阶，再爬1阶 (2)-先爬n-2阶台阶，再爬2阶
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }


    private int[] memo;
    private int help(int n, int[] momo) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (momo[n] == 0) {
            momo[n] = help(n - 1, momo) + help(n - 2, momo);
        }
        return momo[n];
    }
    public int climbStairs2(int n) {
        // 记忆化搜索：还是从上向下的思考方式
        memo = new int[n + 1];
        return help(n, memo);
    }

    public int climbStairs3(int n) {
        // 动态规划(自下向上思考): 先解决子问题，并记录子问题结果
        memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }
}
