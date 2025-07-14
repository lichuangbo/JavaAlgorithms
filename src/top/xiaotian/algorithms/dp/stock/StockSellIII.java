package top.xiaotian.algorithms.dp.stock;

/**
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2：
 *
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 示例 4：
 *
 * 输入：prices = [1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */
public class StockSellIII {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        // dp[i][n]表示第i天处于状态n（0不操作 1第一次持有 2第一次不持有 3第二次持有 4第二次不持有）时，能够获得到的最大钱数
        int[][] dp = new int[len][5];
        // 初始化第0天：不操作=0  第一次持有，说明发生买入行为=-prices[0]  第一次不持有，买-卖=0  第二次持有，买-卖-买=-prices[0]  第二次不持有，买-卖-买-卖=0
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        // 针对第一个元素，考虑当前状态是第一次不持有，只能是当天买并且当天卖
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
    public int maxProfitII(int[] prices) {
        int len = prices.length;
        // dp[i][0]不操作 dp[i][1]第一次持有 dp[i][2]第一次不持有 dp[i][3]第二次持有 dp[i][4]第二次不持有
        int[] dp = new int[5];
        dp[0] = 0;
        dp[1] = -prices[0];
        // 针对第一个元素，当前状态是第一次不持有，只能是当天买并且当天卖
        dp[2] = 0;
        dp[3] = -prices[0];
        dp[4] = 0;
        for (int i = 1; i < len; i++) {
            dp[0] = dp[0];
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
            dp[2] = Math.max(dp[2], dp[1] + prices[i]);
            dp[3] = Math.max(dp[3], dp[2] - prices[i]);
            dp[4] = Math.max(dp[4], dp[3] + prices[i]);
        }
        return dp[4];
    }

    /**
     * 少声明一个状态
     */
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        // dp[i][0]第一次持有 dp[i][1]第一次不持有 dp[i][2]第二次持有 dp[i][3]第二次不持有
        int[] dp = new int[4];
        dp[0] = -prices[0];
        dp[1] = 0;
        dp[2] = -prices[0];
        dp[3] = 0;
        for (int i = 1; i < len; i++) {
            dp[0] = Math.max(dp[0], -prices[i]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
            dp[2] = Math.max(dp[2], dp[1] - prices[i]);
            dp[3] = Math.max(dp[3], dp[2] + prices[i]);
        }
        return dp[3];
    }
}
