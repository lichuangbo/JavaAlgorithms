package top.xiaotian.dataStructures.queue.practice;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/19 10:42
 * @Description: 描述: 127 126
 */
public class NumSquares {
    /**
     * 图论转化：将n到0这n+1个数字表示为节点，两个数字x到y相差一个完全平方数，就连接一条边，全部符合条件的连接完后得到了无权图
     * 只需要求解这个无权图中从n到0的最短路径即可
     * @param n
     * @return
     */
    public int numSquares(int n) {
        Queue<int[]> queue = new LinkedList<>();
        // 0下标存储对应数字节点，1下标存储经过几个节点到达0
        queue.offer(new int[]{n, 0});
        // 缓存，否则每次都会加入队列
        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int num = tmp[0], step = tmp[1];

            // num等于0，返回对应的到达0节点的步数
            if (num == 0) {
                return step;
            }

            // 从1开始看，判断num和i之间是否存在完全平方数
            for (int i = 1; num - i * i >= 0; i++) {
                if (!visited[num - i * i]) {
                    queue.offer(new int[]{num - i * i, step + 1});
                    visited[num - i * i] = true;
                }
            }
        }
        /**
         * [(4, 0)]         (4,0)
         * [(3, 1), (0, 1)] (3,1)
         * [(0, 1), (2, 2)] (0,1)
         * break
         */
        return 0;
    }

    // 递归+记忆化  仿照IntegerBreak实现思路
    public int numSquares2(int n) {
        int[] momo = new int[n + 1];
        help(n, momo);
        return help(n, momo);
    }
    private int help(int n, int[] momo) {
        if (n == 0) {
            return 0;
        }

        // 记忆化操作1
        if (momo[n] != 0) {
            return momo[n];
        }
        int res = Integer.MAX_VALUE;
        /**
         *          4       自顶向下思考，求和为4的完全平方数的最少数量，先采用试探的策略[1, 4 ... ]得到子问题分支树结构
         *        /   \     选1得到3，选2得到0，选3超出范围不考虑。
         *       3     0    此时问题转化为 min(和为3的完全平方数的最少数量, 和为0的完全平方数的最少数量)+1
         *      /           继续画树结构，能够看出 【和为0的完全平方数的最少数量】是递归的出口
         *     2            编码时可以借助循环去处理同一层级的最小值，思路参考343InterBreak
         *    /
         *   1
         *  /
         * 0
         */
        for (int i = 1; n - i * i >= 0; i++) {
            res = Math.min(res, help(n - i * i, momo) + 1);
        }
        // 记忆化操作2
        momo[n] = res;
        return res;
    }

    // 动态规划
    public int numSquares3(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {// 求解dp[i]的值
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; i - j * j >= 0; j++) {// 对i进行拆分
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int res = new NumSquares().numSquares3(19);
        System.out.println(res);
    }
}
