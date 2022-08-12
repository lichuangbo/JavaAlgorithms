package top.xiaotian.algorithms.dp;

public class CoinChange {

  /**
   * 322. 零钱兑换 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
   *
   * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
   *
   * 你可以认为每种硬币的数量是无限的。
   *
   *
   *
   * 示例 1：
   *
   * 输入：coins = [1, 2, 5], amount = 11 输出：3 解释：11 = 5 + 5 + 1 示例 2：
   *
   * 输入：coins = [2], amount = 3 输出：-1 示例 3：
   *
   * 输入：coins = [1], amount = 0 输出：0
   *
   *
   * 提示：
   *
   * 1 <= coins.length <= 12 1 <= coins[i] <= 231 - 1 0 <= amount <= 104
   */
  public int coinChange(int[] coins, int amount) {
    int len = coins.length;
    // dp[i][j]含义：从coins[0, i]中选择硬币，凑成和为j的方案数
    // dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - k * coin] + k)
    int[][] dp = new int[len + 1][amount + 1];
    // 初始化：dp[0][0] = 0, dp[0][j] = Integer.MAX_VALUE
    for (int j = 1; j <= amount; j++) {
      dp[0][j] = Integer.MAX_VALUE;
    }
    for (int i = 1; i <= len; i++) {
      int coin = coins[i - 1];
      for (int j = 0; j <= amount; j++) {
        // dp[i][j]在k的循环中变化了，需要在这里保存
        dp[i][j] = dp[i - 1][j];
        for (int k = 1; k * coin <= j; k++) {
          if (dp[i - 1][j - k * coin] != Integer.MAX_VALUE) {
            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * coin] + k);
          }
        }
      }
    }
    return dp[len][amount] == Integer.MAX_VALUE ? -1 : dp[len][amount];
  }

  public int coinChange2(int[] coins, int amount) {
    // dp[j]表示凑够总金额为j需要的最少钱币数
    int[] dp = new int[amount + 1];
    // 初始化
    for (int j = 1; j <= amount; j++) {
      dp[j] = Integer.MAX_VALUE;
    }
    for (int i = 1; i <= coins.length; i++) {
      int coin = coins[i - 1];
      //正序遍历：完全背包每个硬币可以选择多次
      for (int j = coin; j <= amount; j++) {
        if (dp[j - coin] != Integer.MAX_VALUE) {
          dp[j] = Math.min(dp[j], dp[j - coin] + 1);
        }
      }
    }
    return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
  }

  /**
   * 518. 零钱兑换 II 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
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
   * 输入：amount = 5, coins = [1, 2, 5] 输出：4 解释：有四种方式可以凑成总金额： 5=5 5=2+2+1 5=2+1+1+1 5=1+1+1+1+1 示例 2：
   * <p>
   * 输入：amount = 3, coins = [2] 输出：0 解释：只用面额 2 的硬币不能凑成总金额 3 。 示例 3：
   * <p>
   * 输入：amount = 10, coins = [10] 输出：1
   * <p>
   * <p>
   * 提示：
   * <p>
   * 1 <= coins.length <= 300 1 <= coins[i] <= 5000 coins 中的所有值 互不相同 0 <= amount <= 5000
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
    for (int i = 1; i <= coins.length; i++) {
      int coin = coins[i - 1];
      for (int j = coin; j <= amount; j++) {
        dp[j] += dp[j - coin];
      }
    }
    return dp[amount];
  }
}
