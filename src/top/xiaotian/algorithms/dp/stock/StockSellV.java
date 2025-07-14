package top.xiaotian.algorithms.dp.stock;

/**
 * 309. 买卖股票的最佳时机含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * 示例 2:
 *
 * 输入: prices = [1]
 * 输出: 0
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class StockSellV {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        /**
         * dp[i][n]表示在第i天，处于状态n（0持有股票，1不持有股票，今天卖出，
         * 2不持有股票,并且不是今天卖出的（包含了两个情况：1.两天前卖出了股票，昨天度过一天冷冻期 2. 昨天就是卖出股票状态，今天仍然没操作），3昨天卖出股票，今天冷冻期）时，持有的最大钱数
         * dp[i][0]=max(dp[i-1][0], max(dp[i-1][2]-prices[i], dp[i-1][3]-prices[i])); 之前持有，现在继续持有 or 之前不持有，今天买入（不用管内部是哪个小状态，只要不是今天卖的并且手里没有股票，就能买） or 昨天处于冷冻期，今天买入
         * dp[i][1]=dp[i-1][0] + prices[i]  之前持有，今天卖出
         * dp[i][2]=max(dp[i-1][2], dp[i-1][3])  之前不持有，现在继续不持有 or 两天前卖出，昨天处于冷冻期
         * dp[i][3]=dp[i-1][1]  冷冻期只有一天，一定是昨天卖出
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
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][3]);
            dp[i][3] = dp[i - 1][1];
        }
        return Math.max(dp[len - 1][1], Math.max(dp[len - 1][2], dp[len - 1][3]));
    }

    // 状态压缩
    public int maxProfit2(int[] prices) {
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

    public int maxProfit3(int[] prices) {
        // dp[0]持有股票    dp[1]不持有股票，且当天卖出    dp[2]不持有股票，且当天没有卖出
        int[] dp = new int[3];
        dp[0] = -prices[0];
        dp[1] = 0;
        dp[2] = 0;
        for (int i = 1; i < prices.length; i++) {
            // 暂存一下变量，避免覆盖
            int oldDp0 = dp[0], oldDp1 = dp[1];
            // 前一天就持有，当天不操作 or 当天买入，那么当天买入的话，前一天不能是冷冻期
            dp[0] = Math.max(oldDp0, dp[2] - prices[i]);
            // 当天卖出了股票，那么只有一种情况：前一天持有股票，然后在今天卖出
            dp[1] = oldDp0 + prices[i];
            // 前一天是卖出状态，则当天结束冷冻期，未操作  or  前一天就是未持有未卖出状态，保持
            dp[2] = Math.max(oldDp1, dp[2]);
        }
        return Math.max(dp[1], dp[2]);
    }
}
