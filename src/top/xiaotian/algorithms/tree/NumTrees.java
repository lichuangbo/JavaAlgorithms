package top.xiaotian.algorithms.tree;

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
  public int numTrees(int n) {
    if (n == 0 || n == 1) {
      return 1;
    }

    int total = 0;
    // 每一个数字都有可能是根节点
    for (int i = 1; i <= n; i++) {
      // 递归计数左子节点和右子节点数目
      int left = numTrees(i - 1);
      int right = numTrees(n - i);
      total += (left * right);
    }
    return total;
  }

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

  public int numTrees3(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 1; j <= i; j++) {
        dp[i] += dp[j - 1] * dp[i - j];
      }
    }
    return dp[n];
  }
}
