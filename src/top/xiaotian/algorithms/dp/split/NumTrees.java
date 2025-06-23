package top.xiaotian.algorithms.dp.split;

/**
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 3
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 19
 */
public class NumTrees {

    /**
     * 以n=3为例，他的结果=以1为根的数目+以2为根的数目+以3为根的数目
     * 1为头结点的数量 = 右子树有2个元素的搜索树数量 * 左子树有0个元素的搜索树数量
     * 2为头结点的数量 = 右子树有1个元素的搜索树数量 * 左子树有1个元素的搜索树数量
     * 3为头结点的数量 = 右子树有0个元素的搜索树数量 * 左子树有2个元素的搜索树数量
     * 【右子树有2个元素的搜索树数量】的求解出现了重复，虽然两个子树的结构不同，数值也不同，但是这道题目求解的是不同元素的解法
     */
    // 方法语义：计算n个不同的元素可以组成多少个不同的BST
    public int numTrees(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int total = 0;
        // 每一个数字都有可能是根节点
        for (int i = 1; i <= n; i++) {
            // 递归计数i-1个不同的元素组成左子节点的解法数，[1, i-1]
            int left = numTrees(i - 1);
            // 递归计算n-i个不同的元素组成右子节点的解法数, [i+1, n]
            int right = numTrees(n - i);
            total += (left * right);
        }
        return total;
    }

    // 记忆化
    public int numTrees2(int n) {
        return help(n, new int[n + 1]);
    }

    private int help(int n, int[] memo) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }

        int total = 0;
        for (int i = 1; i <= n; i++) {
            int left = help(i - 1, memo);
            int right = help(n - i, memo);
            total += (left * right);
        }
        memo[n] = total;
        return total;
    }

    /**
     * 动态规划
     * 时间   O(n^2)
     * 空间   O(n)
     */
    public int numTrees3(int n) {
        // dp[i]表示长度为i的序列能构成的二叉搜索树的数量,i=[1,n]
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            // 求解dp[i]，以i为根节点时，左子树序列长度为j-1,右子树序列长度为i-j
            for (int j = 1; j <= i; j++) {// 每一个节点都可以作为根
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
