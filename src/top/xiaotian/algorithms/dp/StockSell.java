package top.xiaotian.algorithms.dp;

/**
 * 买卖股票问题
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/2/9 15:57
 * @Description: 描述:
 */
public class StockSell {

  /**
   * 剑指 Offer 63. 股票的最大利润 121. 买卖股票的最佳时机 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
   * <p>
   * <p>
   * <p>
   * 示例 1:
   * <p>
   * 输入: [7,1,5,3,6,4] 输出: 5 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
   * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。 示例 2:
   * <p>
   * 输入: [7,6,4,3,1] 输出: 0 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
   */
  public int maxProfitI(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    // 状态定义：dp[i]表示以prices[i]为结尾的子数组的最大利润
    // 状态转移方程：dp[i] = Math.max(dp[i - 1], prices[i] - min[0...i])   贪心思想
    // 第i天的最大收益 = max (第i天不操作，取第i-1天的最大收益作为结果) (第i天卖出，但是想卖最好的价格，就得从数组中选出最小的元素)
    int[] dp = new int[prices.length];
    dp[0] = 0;
    int minPrice = prices[0];
    for (int i = 1; i < prices.length; i++) {
      minPrice = Math.min(prices[i], minPrice);
      dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
    }
    return dp[prices.length - 1];
  }

  // 状态压缩
  public int maxProfitI2(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    int res = 0;
    int minPrice = prices[0];
    for (int i = 1; i < prices.length; i++) {
      minPrice = Math.min(prices[i], minPrice);
      res = Math.max(res, prices[i] - minPrice);
    }
    return res;
  }

  public int maxProfitI3(int[] prices) {
    // 状态定义：dp[i][n]表示在第i天处于状态n时，能够获得的最大钱数
    // dp[i][0] = Math.max(dp[i - 1][0], -prices[i])  之前就持有，继续持有 or 第i天买入（只能买一次）
    // dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i])  之前不持有，继续不持有 or 第i天卖出
    int len = prices.length;
    int[][] dp = new int[len][2];
    dp[0][0] = -prices[0];
    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
      dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
    }
    return dp[len - 1][1];
  }

  public int maxProfitI4(int[] prices) {
    int len = prices.length;
    // 状态压缩
    int dp0 = -prices[0];
    int dp1 = 0;
    for (int i = 1; i < len; i++) {
      dp0 = Math.max(dp0, -prices[i]);
      dp1 = Math.max(dp1, dp0 + prices[i]);
    }
    return dp1;
  }

  /**
   * 122. 买卖股票的最佳时机 II 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
   * <p>
   * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
   * <p>
   * 返回 你能获得的 最大 利润 。
   * <p>
   * <p>
   * <p>
   * 示例 1：
   * <p>
   * 输入：prices = [7,1,5,3,6,4] 输出：7 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 -
   * 1 = 4 。 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。 总利润为 4 + 3 = 7
   * 。
   */
  public int maxProfitII(int[] prices) {
    // dp[i][2]表示第i天处于状态n时，能够获得到的最大钱数
    // dp[i][0]第i天持有股票，dp[i][0]=max(dp[i-1][0], dp[i-1][1] - prices[i]) 之前就有，现在继续持有 or 在第i天买入
    // dp[i][1]第i天没有股票，dp[i][1]=max(dp[i-1][1], dp[i-1][0] + prices[i]) 之前没有，继续不持有 or 第i天卖出
    int len = prices.length;
    int[][] dp = new int[len][2];
    dp[0][0] = -prices[0];
    dp[0][1] = 0;
    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
      dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
    }
    return dp[len - 1][1];
  }

  // 状态压缩
  public int maxProfitII2(int[] prices) {
    int len = prices.length;
    int dp0 = -prices[0];
    int dp1 = 0;
    for (int i = 1; i < len; i++) {
      dp0 = Math.max(dp0, dp1 - prices[i]);
      dp1 = Math.max(dp1, dp0 + prices[i]);
    }
    return dp1;
  }

  /**
   * 123. 买卖股票的最佳时机 III 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
   * <p>
   * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
   * <p>
   * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
   * <p>
   * <p>
   * <p>
   * 示例 1:
   * <p>
   * 输入：prices = [3,3,5,0,0,3,1,4] 输出：6 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 =
   * 3-0 = 3 。 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
   */
  public int maxProfitIII(int[] prices) {
    int len = prices.length;
    // dp[i][n]表示第i天处于状态n时，能够获得到的最大钱数
    // n=0: 没有操作， 1：第一次买入（持有） 2：第一次卖出 3：第二次买入 4：第二次卖出
    int[][] dp = new int[len][5];
    dp[0][0] = 0;
    dp[0][1] = -prices[0];
    dp[0][2] = 0;
    dp[0][3] = -prices[0];
    dp[0][4] = 0;
    for (int i = 1; i < len; i++) {
      dp[i][0] = dp[i - 1][0];
      dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); // 之前持有，现在继续持有 or 第i天买入
      dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]); // 之前不持有，现在继续 or 第i天卖出
      dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]); // 之前持有，现在继续持有 or 第i天买入
      dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]); // 之前不持有，现在继续 or 第i天卖出
    }
    return dp[len - 1][4];
  }

  // 状态压缩
  public int maxProfitIII2(int[] prices) {
    int len = prices.length;
    int dp0 = 0;
    int dp1 = -prices[0];
    int dp2 = 0;
    int dp3 = -prices[0];
    int dp4 = 0;
    for (int i = 1; i < len; i++) {
      dp1 = Math.max(dp1, dp0 - prices[i]);
      dp2 = Math.max(dp2, dp1 + prices[i]);
      dp3 = Math.max(dp3, dp2 - prices[i]);
      dp4 = Math.max(dp4, dp3 + prices[i]);
    }
    return dp4;
  }

  public int maxProfitIII3(int[] prices) {
    int len = prices.length;
    int[][] dp = new int[len][5];

    // 初始化
    for (int j = 1; j < 5; j += 2) {
      dp[0][j] = -prices[0];
    }

    for (int i = 1; i < len; i++) {
      for (int j = 0; j < 3; j += 2) {
        dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);// 奇数时为持有股票状态：之前持有，现在继续持有 or 之前没有，今天买入
        dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);// 偶数为不持有股票状态：之前不持有，现在继续不持有 or 之前持有，今天卖出
      }
    }
    return dp[len - 1][4];
  }

  /**
   * 188. 买卖股票的最佳时机 IV 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
   * <p>
   * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
   * <p>
   * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
   * <p>
   * <p>
   * <p>
   * 示例 1：
   * <p>
   * 输入：k = 2, prices = [2,4,1] 输出：2 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 =
   * 4-2 = 2 。
   */
  public int maxProfitIV(int k, int[] prices) {
    int len = prices.length;
    if (len == 0) {
      return 0;
    }
    // dp[i][j][n] 第i天用了j笔交易所处状态n，获取到的最大钱数
    // dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j-1][1]-prices[i]) 之前持有，现在继续持有 or 第i天买入(买入算一笔交易)
    // dp[i][j][1] = max(dp[i-1][j][1], dp[i-1][j][0]+prices[i]) 之前不持有，现在继续 or 第i天卖出
    int[][][] dp = new int[len][k + 1][2];
    for (int j = 0; j <= k; j++) {
      dp[0][j][0] = -prices[0];
    }
    for (int i = 1; i < len; i++) {
      for (int j = 1; j <= k; j++) {
        dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] - prices[i]);
        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] + prices[i]);
      }
    }
    return dp[len - 1][k][1];
  }

  public int maxProfitIV2(int k, int[] prices) {
    // dp[i][n]表示第i天处于状态n时，获取到的最大钱数
    // 奇数表示第 k 次交易持有股票, 偶数表示第 k 次交易不持有, 0 表示没有操作
    int len = prices.length;
    if (len == 0) {
      return 0;
    }
    // 最多有k笔交易，买卖各占一次，不操作占一次，存在2*k+1个状态
    int[][] dp = new int[len][k * 2 + 1];

    // 初始化
    for (int j = 1; j < k * 2 + 1; j += 2) {
      dp[0][j] = -prices[0];
    }

    for (int i = 1; i < len; i++) {
      for (int j = 0; j < k * 2 - 1; j += 2) {
        dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);// 奇数时为持有股票状态：之前持有，现在继续持有 or 之前没有，今天买入
        dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);// 偶数为不持有股票状态：之前不持有，现在继续不持有 or 之前持有，今天卖出
      }
    }
    return dp[len - 1][k * 2];
  }

  /**
   * 309. 最佳买卖股票时机含冷冻期
   * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
   * <p>
   * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
   * <p>
   * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
   * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
   * <p>
   * <p>
   * <p>
   * 示例 1:
   * <p>
   * 输入: prices = [1,2,3,0,2]
   * 输出: 3
   * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
   */
  public int maxProfitV(int[] prices) {
    int len = prices.length;
    // dp[i][n]表示在第i天，处于状态n时，持有的最大钱数
    // n=[持有股票，不持有股票两天前卖出，不持有股票今天卖出，冷冻期]
    int[][] dp = new int[len][4];
    dp[0][0] = -prices[0];

    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] - prices[i], dp[i - 1][3] - prices[i])); // 之前持有，现在继续持有 or 之前不持有，今天买入 or 之前处于冷冻期，今天买入
      dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]); // 之前不持有，现在继续不持有 or 两天前卖出，昨天处于冷冻期
      dp[i][2] = dp[i - 1][0] + prices[i]; // 之前持有，今天卖出
      dp[i][3] = dp[i - 1][2]; // 昨天卖出
    }
    return Math.max(dp[len - 1][1], Math.max(dp[len - 1][2], dp[len - 1][3]));
  }

  // 状态压缩
  public int maxProfitV2(int[] prices) {
    int[] dp = new int[4];

    dp[0] = -prices[0];
    dp[1] = 0;
    for (int i = 1; i < prices.length; i++) {
      // 使用临时变量来保存dp[0], dp[2]
      // 因为马上dp[0]和dp[2]的数据都会变
      int tmp0 = dp[0];
      int tmp2 = dp[2];
      dp[0] = Math.max(dp[0], Math.max(dp[3] - prices[i], dp[1] - prices[i]));
      dp[1] = Math.max(dp[1], dp[3]);
      dp[2] = tmp0 + prices[i];
      dp[3] = tmp2;
    }
    return Math.max(dp[1], Math.max(dp[2], dp[3]));
  }

  /**
   * 714. 买卖股票的最佳时机含手续费
   * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
   *
   * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
   *
   * 返回获得利润的最大值。
   *
   * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
   *
   *
   *
   * 示例 1：
   *
   * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
   * 输出：8
   * 解释：能够达到的最大利润:
   * 在此处买入 prices[0] = 1
   * 在此处卖出 prices[3] = 8
   * 在此处买入 prices[4] = 4
   * 在此处卖出 prices[5] = 9
   * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
   */
  public int maxProfitVI(int[] prices, int fee) {
    int len = prices.length;
    // dp[i][n]表示第i天处于状态n时，能够获得到的最大钱数
    // dp[i][0]第i天持有股票，dp[i][0]=max(dp[i-1][0], dp[i-1][1] - prices[i]) 之前就有，现在继续持有 or 在第i天买入
    // dp[i][1]第i天没有股票，dp[i][1]=max(dp[i-1][1], dp[i-1][0] + prices[i] - fee) 之前没有，继续不持有 or 第i天卖出
    int[][] dp = new int[len][2];
    dp[0][0] = -prices[0];
    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
      dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
    }
    return dp[len - 1][1];
  }

  // 状态压缩
  public int maxProfitVI2(int[] prices, int fee) {
    int len = prices.length;
    int dp0 = -prices[0];
    int dp1 = 0;
    for (int i = 1; i < len; i++) {
      dp0 = Math.max(dp0, dp1 - prices[i]);
      dp1 = Math.max(dp1, dp0 + prices[i] - fee);
    }
    return dp1;
  }
}
