package top.xiaotian.algorithms.dp.stock;

/**
 * 188. 买卖股票的最佳时机 IV
 * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 *
 * 提示：
 *
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class StockSellIV {

    public int maxProfit(int k, int[] prices) {
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
            // 每一次内部循环，会赋两个值，所以j循环步长为2
            for (int j = 0; j < k * 2 - 1; j += 2) {
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);// 奇数时为持有股票状态：之前持有，现在继续持有 or 之前没有，今天买入
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);// 偶数为不持有股票状态：之前不持有，现在继续不持有 or 之前持有，今天卖出
            }
        }
        // 收益最大是最后一天第2k次不持有股票
        return dp[len - 1][k * 2];
    }

    public int maxProfit2(int k, int[] prices) {
        // 奇数持有 偶数不持有
        int[] dp = new int[2 * k + 1];
        for (int j = 1; j < 2 * k + 1; j += 2) {
            dp[j] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2 * k - 1; j += 2) {
                dp[j + 1] = Math.max(dp[j + 1], dp[j] - prices[i]);
                dp[j + 2] = Math.max(dp[j + 2], dp[j + 1] + prices[i]);
            }
        }
        return dp[2 * k];
    }
}
