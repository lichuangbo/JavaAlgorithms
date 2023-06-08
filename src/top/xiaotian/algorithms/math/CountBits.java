package top.xiaotian.algorithms.math;

/**
 * 338. 比特位计数
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 105
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 * 你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ）
 */
public class CountBits {
    /**
     * 时间O(nlogn)
     * 利用n&(n-1)二进制算法计算1的数量
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int count1 = 0;
            int tmp = i;
            while (tmp != 0) {
                tmp = tmp & (tmp - 1);
                count1++;
            }
            res[i] = count1;
        }
        return res;
    }

    /**
     * 动态规划
     * n的二进制比n&(n-1)的二进制中的1多1个，状态转移公式：dp[i] = dp[i&(i-1)] + 1
     */
    public int[] countBits2(int n) {
        int[] dp = new int[n + 1];
        // 初始化：dp[0]=0，可以直接取默认值
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i & (i - 1)] + 1;
        }
        return dp;
    }
}
