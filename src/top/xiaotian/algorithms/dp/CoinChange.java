package top.xiaotian.algorithms.dp;

public class CoinChange {
  /**
   * 518. 零钱兑换 II
   * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
   * <p>
   * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
   * <p>
   * 假设每一种面额的硬币有无限个。
   * <p>
   * 题目数据保证结果符合 32 位带符号整数。
   * <p>
   * <p>
   * <p>
   * 示例 1：
   * <p>
   * 输入：amount = 5, coins = [1, 2, 5]
   * 输出：4
   * 解释：有四种方式可以凑成总金额：
   * 5=5
   * 5=2+2+1
   * 5=2+1+1+1
   * 5=1+1+1+1+1
   * 示例 2：
   * <p>
   * 输入：amount = 3, coins = [2]
   * 输出：0
   * 解释：只用面额 2 的硬币不能凑成总金额 3 。
   * 示例 3：
   * <p>
   * 输入：amount = 10, coins = [10]
   * 输出：1
   * <p>
   * <p>
   * 提示：
   * <p>
   * 1 <= coins.length <= 300
   * 1 <= coins[i] <= 5000
   * coins 中的所有值 互不相同
   * 0 <= amount <= 5000
   */
  public int change(int amount, int[] coins) {
    // dp[i][j]表示在coins[0, i]区间中凑成总金额为j的货币组合数目
    int len = coins.length;
    int[][] dp = new int[len + 1][amount + 1];
    // 初始化：凑成金额为0的组合只有一种
    dp[0][0] = 1;
    for (int i = 1; i <= len; i++) {
      int val = coins[i - 1];
      for (int j = 0; j <= amount; j++) {
        dp[i][j] = dp[i - 1][j];
        // 每个硬币可以选择多次
        for (int k = 1; k * val <= j; k++) {
          dp[i][j] += dp[i - 1][j - k * val];
        }
      }
    }
    return dp[len][amount];
  }

  public int change2(int amount, int[] coins) {
    // dp[j]表示凑成总金额为j的货币组合数目
    int[] dp = new int[amount + 1];
    // 初始化：凑成金额为0的组合只有一种
    dp[0] = 1;
    for (int i = 0; i < coins.length; i++) {
      for (int j = coins[i]; j <= amount; j++) {
        dp[j] += dp[j - coins[i]];
      }
    }
    return dp[amount];
  }
}
