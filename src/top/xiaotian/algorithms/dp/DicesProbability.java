package top.xiaotian.algorithms.dp;

import java.util.Arrays;

/**
 * 剑指 Offer 60. n个骰子的点数
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *
 *
 *
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 *
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 *
 *
 * 限制：
 *
 * 1 <= n <= 11
 */
public class DicesProbability {
  public double[] dicesProbability(int n) {
    // 6n - n + 1是投掷n个骰子出现的点数的范围区间
    double[] ans = new double[5 * n + 1];
    // 投掷n个骰子出现的组合次数
    double total = Math.pow(6, n);
    // 状态定义：dp[i][j]表示投掷完第i枚骰子后，点数j（指总和）出现的次数
    // 状态转移：dp[i][j] = dp[i-1][j-1] + dp[i-1][j-2] + ... + dp[i-1][j-6]
    int[][] dp = new int[n + 1][6 * n + 1];
    // 状态初始化：dp[1][x]投掷一个骰子时每一个点数出现的次数相等都是1
    for (int j = 1; j <= 6; j++) {
      dp[1][j] = 1;
    }
    for (int i = 1; i <= n; i++) {
      for (int j = i; j <= 6 * n; j++) {
        for (int k = 1; k <= 6; k++) {
          // 因为j-k会出现越界情况，如计算dp[1][1],需要计算dp[0][-1],当遇到越界，填充0
          dp[i][j] += (j >= k) ? dp[i - 1][j - k] : 0;
          // 遍历到最后一行，开始填充ans数组
          if (i == n) {
            ans[j - i] = dp[i][j] / total;
          }
        }
      }
    }

    return ans;
  }

  // 正向递推：遍历dp[i-1],计算每项dp[i-1,j]对概率dp[i][j+1], dp[i][j+2]...dp[i][j+6]产生的贡献
  public double[] dicesProbability2(int n) {
    double[] dp = new double[6];
    Arrays.fill(dp, 1.0 / 6.0);

    for (int i = 2; i <= n; i++) {
      double[] tmp = new double[5 * i + 1];
      for (int j = 0; j < dp.length; j++) {
        for (int k = 1; k <= 6; k++) {
          // 乘1/6是每个点出现的概率
          tmp[j + k - 1] += dp[j] / 6.0;
        }
      }
      dp = tmp;
    }
    return dp;
  }

  public static void main(String[] args) {
    double[] doubles = new DicesProbability().dicesProbability2(3);
    System.out.println(Arrays.toString(doubles));
  }
}
