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
   * 剑指 Offer 63. 股票的最大利润
   * 121. 买卖股票的最佳时机（买卖一次）
   * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
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
  // 贪心法
  public int maxProfitI(int[] prices) {
    // 只有一次买卖机会，假如在第i天卖出，只要找到前i天的最小价格（模拟最低价格买入）
    int res = 0;
    int minPrice = Integer.MAX_VALUE;
    for (int i = 0; i < prices.length; i++) {
      minPrice = Math.min(prices[i], minPrice);
      res = Math.max(res, prices[i] - minPrice);
    }
    return res;
  }

  public int maxProfitI2(int[] prices) {
    // 状态定义：dp[i][n]表示在第i天处于状态n（n=0持有状态，n=1不持有状态）时，能够获得的最大钱数
    // dp[i][0] = Math.max(dp[i - 1][0], -prices[i])  之前就持有，继续持有 or 第i天买入（只能买一次）
    // dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i])  之前不持有，继续不持有 or 之前持有，第i天卖出
    int len = prices.length;
    int[][] dp = new int[len][2];
    // 状态初始化：第0天持有，说明发生买入行为初始化为-prices[0]  第0天不持有，保持现有钱数0
    dp[0][0] = -prices[0];
    dp[0][1] = 0;
    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
      dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
    }
    // 收益最大是第i天不持有股票
    return dp[len - 1][1];
  }

  // 状态压缩
  public int maxProfitI3(int[] prices) {
    int len = prices.length;
    int dp0 = -prices[0];
    int dp1 = 0;
    for (int i = 1; i < len; i++) {
      dp0 = Math.max(dp0, -prices[i]);
      dp1 = Math.max(dp1, dp0 + prices[i]);
    }
    return dp1;
  }

  /**
   * 122. 买卖股票的最佳时机 II（买卖多次）
   * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
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
    // 状态定义：dp[i][n]表示第i天处于状态n时，能够获得到的最大钱数
    // dp[i][0]第i天持有股票，dp[i][0]=max(dp[i-1][0], dp[i-1][1] - prices[i]) 之前就持有，现在继续持有 or 之前不持有，在第i天买入（能买卖多次，dp[i-1][1]中可能有利润）
    // dp[i][1]第i天没有股票，dp[i][1]=max(dp[i-1][1], dp[i-1][0] + prices[i]) 之前不持有，继续不持有 or 之前持有，第i天卖出
    int len = prices.length;
    int[][] dp = new int[len][2];
    // 初始化：第0天持有，说明发生买入行为初始化为-prices[0]  第0天不持有，保持现有钱数0
    dp[0][0] = -prices[0];
    dp[0][1] = 0;
    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
      dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
    }
    // 收益最大是第i天不持有股票
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
   * 123. 买卖股票的最佳时机 III（买卖两次）
   * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
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
    // dp[i][n]表示第i天处于状态n（0不操作 1第一次持有 2第一次不持有 3第二次持有 4第二次不持有）时，能够获得到的最大钱数
    int[][] dp = new int[len][5];
    // 初始化第0天：不操作=0  第一次持有，说明发生买入行为=-prices[0]  第一次不持有，买-卖=0  第二次持有，买-卖-买=-prices[0]  第二次不持有，买-卖-买-卖=0
    dp[0][0] = 0;
    dp[0][1] = -prices[0];
    dp[0][2] = 0;
    dp[0][3] = -prices[0];
    dp[0][4] = 0;
    for (int i = 1; i < len; i++) {
      dp[i][0] = dp[i - 1][0];
      dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); // 之前持有，现在继续持有 or 第i天买入
      dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]); // 之前不持有，现在继续不持有 or 第i天卖出
      dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]); // 之前持有，现在继续持有 or 第i天买入
      dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]); // 之前不持有，现在继续不持有 or 第i天卖出
    }
    // 收益最大是第i天第二次不持有股票  如果第一次卖出已经是最大值了，那么我们可以在当天立刻买入再立刻卖出, 也就是第二次不持有状态包含了第一次不持有状态
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

  /**
   * 188. 买卖股票的最佳时机 IV （买卖k次）
   * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
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
    // dp[i][j][n] 第i天发生了j笔买卖所处状态n（0持有，1不持有），获取到的最大钱数
    // dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j-1][1]-prices[i]) 之前持有，现在继续持有 or 之前不持有dp[i-1][][1],第i天买入(买入算一笔交易)
    // dp[i][j][1] = max(dp[i-1][j][1], dp[i-1][j][0]+prices[i]) 之前不持有，现在继续不持有 or 之前持有dp[i-1][][0],第i天卖出(因为买入已经-1了，卖出不用-1)
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
    // dp[i][n]表示第i天处于状态n（0表示没有操作，奇数表示第k次持有, 偶数表示第k次不持有）时，获取到的最大钱数
    int len = prices.length;
    if (len == 0) {
      return 0;
    }
    // 最多有k笔交易，持有不持有各占一次，不操作占一次，存在2*k+1个状态
    int[][] dp = new int[len][k * 2 + 1];

    // 初始化：奇数状态时是持有，都初始化为-prices[0]   偶数状态时是不持有，都初始化为0
    for (int j = 1; j < k * 2 + 1; j += 2) {
      dp[0][j] = -prices[0];
    }

    for (int i = 1; i < len; i++) {
      for (int j = 0; j < k * 2 - 1; j += 2) {
        dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);// 奇数时为持有股票状态：之前持有，现在继续持有 or 之前没有，今天买入
        dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);// 偶数为不持有股票状态：之前不持有，现在继续不持有 or 之前持有，今天卖出
      }
    }
    // 收益最大是第i天第2k次不持有股票
    return dp[len - 1][k * 2];
  }

  /**
   * 309. 最佳买卖股票时机含冷冻期 （多次买卖，但是有卖出冷冻期）
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
    /**
     * dp[i][n]表示在第i天，处于状态n（0持有股票，1不持有股票，今天卖出，
     * 2不持有股票,并且不是今天卖出的（包含了两个情况：1.两天前卖出了股票，度过一天冷冻期 2. 前一天就是卖出股票状态，一直没操作），3冷冻期）时，持有的最大钱数
     * dp[i][0]=max(dp[i-1][0], max(dp[i-1][2]-prices[i], dp[i-1][3]-prices[i])); 之前持有，现在继续持有 or 之前不持有，今天买入（小2状态） or 昨天处于冷冻期，今天买入（小1状态）
     * dp[i][1]=dp[i-1][0] + prices[i]  之前持有，今天卖出
     * dp[i][2]=max(dp[i-1][1], dp[i-1][3])  之前不持有，现在继续不持有 or 两天前卖出，昨天处于冷冻期
     * dp[i][3]=dp[i-1][2]  冷冻期只有一天，一定是昨天卖出
     */
    int[][] dp = new int[len][4];
    dp[0][0] = -prices[0];
    /**
     * 不持有股票，并且不是今天卖出的，从定义来说，很难明确应该初始多少，这种情况我们就看递推公式需要我们给他初始成什么数值。
     * 如果i为1，dp[i-1][2]-prices[i]，即dp[0][2]-prices[1]，那么大家感受一下 dp[0][2]应该初始成多少，只能初始为0。想一想如果初始为其他数值，是我们第1天买入股票后 手里还剩的现金数量是不是就不对了
     */

    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3] - prices[i]));
      dp[i][1] = dp[i - 1][0] + prices[i];
      dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][3]);
      dp[i][3] = dp[i - 1][2];
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
   * 714. 买卖股票的最佳时机含手续费 （买卖手续费）
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
