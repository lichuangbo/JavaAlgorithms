package top.xiaotian.algorithms.dp.stock;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 */
public class StockSell {
    /**
     * 贪心
     * 时间   O(n)
     * 空间   O(1)
     * 只有一次买卖机会，假如在第i天卖出，只要找到前i天的最小价格（模拟最低价格买入）
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(prices[i], minPrice);
            res = Math.max(res, prices[i] - minPrice);
        }
        return res;
    }

    /**
     * 动态规划
     * 时间   O(n)
     * 空间   O(n)
     * 状态定义：dp[i][n]表示在第i天处于状态n（n=0持有状态，n=1不持有状态）时，能够获得的最大钱数
     * dp[i][0] = Math.max(dp[i - 1][0], -prices[i])  之前就持有，继续持有 or 第i天买入（只能买一次）
     * dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i])  之前不持有，继续不持有 or 之前持有，第i天卖出
     */
    public int maxProfitII(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        // 状态初始化：第0天持有，说明发生买入行为初始化为-prices[0]  第0天不持有，保持现有钱数0
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        // 收益最大是不持有股票时候
        return dp[len - 1][1];
    }

    /**
     * 状态压缩
     * 时间   O(n)
     * 空间   O(1)
     */
    public int maxProfitIII(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[2][2];

        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < len; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], -prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], prices[i] + dp[(i - 1) % 2][0]);
        }
        return dp[(len - 1) % 2][1];
    }

    public int maxProfitIV(int[] prices) {
        int[] dp = new int[2];
        dp[0] = -prices[0];
        dp[1] = 0;
        for (int i = 1; i <= prices.length; i++) {
            // 前一天持有；或当天买入
            dp[0] = Math.max(dp[0], -prices[i - 1]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
        }
        return dp[1];
    }
}
